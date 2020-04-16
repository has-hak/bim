package am.nuaca.bim.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import am.nuaca.bim.application.model.BudgetDocument;
import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.Unit;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class BudgetDocumentReader {

	private static final Set<Tuple2<String, String>> KNOWN_HEADERS = Set.of(Tuple.of("height", "բարձրություն"),
			Tuple.of("thickness", "հաստություն"), Tuple.of("area", "մակերես"), Tuple.of("volume", "ծավալ"));

	private final InputStream fileStream;

	public BudgetDocumentReader(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public BudgetDocument read() throws IOException {
		Workbook workbook = new HSSFWorkbook(fileStream);
		Sheet firstSheet = workbook.getSheetAt(0);

		String compilationName = readCompilationName(firstSheet);

		Row headersRow = firstSheet.getRow(1);
		Row valuesRow = firstSheet.getRow(2);
		Map<String, Double> properties = new HashMap<>();

		int i = -1;
		for (Cell headerCell : headersRow) {
			i++;
			Cell valueCell = valuesRow.getCell(i);

			if (valueCell.getCellType() != CellType.NUMERIC) {
				continue;
			}
			String header = headerCell.getStringCellValue().strip().toLowerCase();
			double value = valueCell.getNumericCellValue();

			properties.put(header, value);

		}

		Map<String, Measure> measures = new HashMap<>();

		properties.forEach((measure, value) -> {
			Optional<Tuple2<String, String>> optionalS = matchWithAnyHeader(measure);
			if (optionalS.isPresent()) {
				Tuple2<String, String> foundHeader = optionalS.get();
				String headerKey = foundHeader._1;
				String headerName = foundHeader._2;
				String unitString = measure.substring(measure.indexOf(headerName) + headerName.length())
						.strip()
						.replaceAll("[()]", "");
				Unit unit = Unit.fromString(unitString);
				measures.put(headerKey, new Measure(unit, value));
			}
		});

		return new BudgetDocument(compilationName, measures.get("height"), measures.get("thickness"),
				measures.get("area"), measures.get("volume"));
	}

	public String readCompilationName(Sheet sheet) {
		Cell cell = sheet.getRow(0).getCell(0);
		return cell.getStringCellValue();
	}

	private static Optional<Tuple2<String, String>> matchWithAnyHeader(String header) {
		return KNOWN_HEADERS.stream().filter(s -> header.contains(s._2)).findFirst();
	}
}
