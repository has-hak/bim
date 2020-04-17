package am.nuaca.bim.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import am.nuaca.bim.application.model.BudgetDocument;
import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.Unit;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class BudgetDocumentReader {

	private static final String NAME_HEADER = "անվանում";

	private static final Set<Tuple2<MeasureType, String>> KNOWN_HEADERS = Set.of(
			Tuple.of(MeasureType.HEIGHT, "բարձրություն"), Tuple.of(MeasureType.THICKNESS, "հաստություն"),
			Tuple.of(MeasureType.AREA, "մակերես"), Tuple.of(MeasureType.VOLUME, "ծավալ"));

	private final InputStream fileStream;

	public BudgetDocumentReader(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public BudgetDocument read() throws IOException {
		Workbook workbook = new HSSFWorkbook(fileStream);
		Sheet firstSheet = workbook.getSheetAt(0);

		String compilationName = readCompilationName(firstSheet);
		String resourceName = "";

		Row headersRow = firstSheet.getRow(1);
		Row valuesRow = firstSheet.getRow(2);
		Map<String, Double> properties = new HashMap<>();

		int i = -1;
		for (Cell headerCell : headersRow) {
			i++;
			Cell valueCell = valuesRow.getCell(i);

			String header = headerCell.getStringCellValue().strip().toLowerCase();

			if (valueCell.getCellType() == CellType.STRING) {
				if (header.equals(NAME_HEADER)) {
					resourceName = valueCell.getStringCellValue();
				}
				continue;
			}

			if (valueCell.getCellType() != CellType.NUMERIC) {
				continue;
			}
			double value = valueCell.getNumericCellValue();

			properties.put(header, value);

		}

		List<Measure> measures = new ArrayList<>();

		properties.forEach((measure, value) -> {
			Optional<Tuple2<MeasureType, String>> optionalS = matchWithAnyHeader(measure);
			if (optionalS.isPresent()) {
				Tuple2<MeasureType, String> foundHeader = optionalS.get();
				MeasureType headerKey = foundHeader._1;
				String headerName = foundHeader._2;
				String unitString = measure.substring(measure.indexOf(headerName) + headerName.length())
						.strip()
						.replaceAll("[()]", "");
				Unit unit = Unit.fromString(unitString);
				measures.add(new Measure(headerKey, unit, value));
			}
		});

		return new BudgetDocument(compilationName, resourceName, measures);
	}

	public String readCompilationName(Sheet sheet) {
		Cell cell = sheet.getRow(0).getCell(0);
		return cell.getStringCellValue();
	}

	private static Optional<Tuple2<MeasureType, String>> matchWithAnyHeader(String header) {
		return KNOWN_HEADERS.stream().filter(s -> header.contains(s._2)).findFirst();
	}
}
