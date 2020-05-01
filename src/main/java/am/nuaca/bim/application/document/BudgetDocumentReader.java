package am.nuaca.bim.application.document;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import am.nuaca.bim.application.model.*;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class BudgetDocumentReader {

	private static final String RESOURCE_TITLE_HEADER = "անվանում";
	private static final String HEIGHT_HEADER = "բարձրություն";
	private static final String THICKNESS_HEADER = "հաստություն";
	private static final String AREA_HEADER = "մակերես";
	private static final String VOLUME_HEADER = "ծավալ";

	private static final Set<String> KNOWN_HEADERS = Set.of(RESOURCE_TITLE_HEADER, HEIGHT_HEADER, THICKNESS_HEADER,
			AREA_HEADER, VOLUME_HEADER);

	private static final Set<Tuple2<MeasureType, String>> KNOWN_MEASURE_HEADERS = Set.of(
			Tuple.of(MeasureType.HEIGHT, HEIGHT_HEADER), Tuple.of(MeasureType.THICKNESS, THICKNESS_HEADER),
			Tuple.of(MeasureType.AREA, AREA_HEADER), Tuple.of(MeasureType.VOLUME, VOLUME_HEADER));

	private static final Map<String, Set<CellType>> SUPPORTED_CELL_TYPES = Map.of(RESOURCE_TITLE_HEADER,
			Set.of(CellType.STRING), HEIGHT_HEADER, Set.of(CellType.NUMERIC), THICKNESS_HEADER,
			Set.of(CellType.NUMERIC), AREA_HEADER, Set.of(CellType.NUMERIC), VOLUME_HEADER, Set.of(CellType.NUMERIC));

	private final InputStream fileStream;

	public BudgetDocumentReader(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public BudgetDocument read() throws IOException {
		Workbook workbook = new HSSFWorkbook(fileStream);
		Sheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = firstSheet.rowIterator();
		Row compilationNameRow = rowIterator.next();
		Cell compilationNameCell = compilationNameRow.getCell(0);
		if (compilationNameCell.getCellType() != CellType.STRING) {
			throw new InvalidBudgetDocumentException("First row first cell must be String");
		}

		String compilationName = compilationNameCell.getStringCellValue();

		Row headersRow = rowIterator.next();

		List<String> headers = new ArrayList<>();

		Map<String, MeasureUnit> unitsOfMeasures = new HashMap<>();

		headersRow.forEach(cell -> {
			CellType cellType = cell.getCellType();
			if (cellType != CellType.STRING) {
				throw new InvalidBudgetDocumentException(
						String.format("Header cell type must be string, but was %s", cellType));
			}

			String header = cell.getStringCellValue().strip().toLowerCase();

			if (KNOWN_HEADERS.contains(header)) {
				headers.add(header);
			}
			else {
				String foundHeader = KNOWN_MEASURE_HEADERS.stream()
						.map(Tuple2::_2)
						.filter(header::contains)
						.findFirst()
						.orElseThrow(
								() -> new InvalidBudgetDocumentException(String.format("Unknown header %s", header)));

				String unitString = header.substring(header.indexOf(foundHeader) + foundHeader.length())
						.strip()
						.replaceAll("[()]", "");

				MeasureUnit measureUnit = MeasureUnit.fromString(unitString);

				headers.add(foundHeader);
				unitsOfMeasures.put(foundHeader, measureUnit);
			}
		});

		List<Map<String, Object>> data = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Map<String, Object> rowData = new HashMap<>();
			for (int i = 0; i < headers.size(); i++) {
				String header = headers.get(i);
				Cell cell = row.getCell(i);
				if (cell == null) {
					rowData.put(header, null);
				}
				else {
					CellType cellType = cell.getCellType();

					Set<CellType> cellTypes = SUPPORTED_CELL_TYPES.get(header);
					if (!cellTypes.contains(cellType)) {
						throw new InvalidBudgetDocumentException(
								String.format("Invalid cell type %s for header %s. Supported types are %s", cellType,
										header, cellTypes));
					}

					Object cellValue = getCellValue(cell);

					rowData.put(header, cellValue);
				}
			}

			data.add(rowData);
		}

		List<BudgetDocumentResource> resources = new ArrayList<>(data.size());

		for (Map<String, Object> rowData : data) {
			String resourceTitle = (String) rowData.get(RESOURCE_TITLE_HEADER);
			if (resourceTitle == null) {
				continue;
			}

			List<Measure> measures = new ArrayList<>();

			for (Tuple2<MeasureType, String> measureHeader : KNOWN_MEASURE_HEADERS) {
				MeasureType measureType = measureHeader._1;
				String measureName = measureHeader._2;
				Double measureValue = (Double) rowData.get(measureName);
				if (measureValue != null) {
					measures.add(new Measure(measureType, unitsOfMeasures.get(measureName), measureValue));
				}
			}


			BudgetDocumentResource budgetDocumentResource = new BudgetDocumentResource(resourceTitle, measures);

			resources.add(budgetDocumentResource);
		}

		return new BudgetDocument(compilationName, resources);
	}

	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		switch (cellType) {
			case BLANK:
			case STRING:
				return cell.getStringCellValue();
			case BOOLEAN:
				return cell.getBooleanCellValue();
			case FORMULA:
				return cell.getCellFormula();
			case NUMERIC:
				return cell.getNumericCellValue();
			default:
				throw new IllegalStateException(String.format("Unsupported cell type %s ", cellType));
		}
	}
}
