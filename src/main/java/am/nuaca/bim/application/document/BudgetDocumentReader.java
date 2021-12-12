package am.nuaca.bim.application.document;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import am.nuaca.bim.application.model.*;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public class BudgetDocumentReader {

	private static final String RESOURCE_TITLE_HEADER = "անվանում";
	private static final String HEIGHT_HEADER = "բարձրություն";
	private static final String THICKNESS_HEADER = "հաստություն";
	private static final String AREA_HEADER = "մակերես";
	private static final String VOLUME_HEADER = "ծավալ";
	private static final String WEIGHT_HEADER = "քաշ";

	private static final Set<String> KNOWN_HEADERS = Set.of(RESOURCE_TITLE_HEADER, HEIGHT_HEADER, THICKNESS_HEADER,
			AREA_HEADER, VOLUME_HEADER, WEIGHT_HEADER);

	private static final Set<Tuple2<MeasureType, String>> KNOWN_MEASURE_HEADERS = Set.of(
			Tuple.of(MeasureType.HEIGHT, HEIGHT_HEADER), Tuple.of(MeasureType.THICKNESS, THICKNESS_HEADER),
			Tuple.of(MeasureType.AREA, AREA_HEADER), Tuple.of(MeasureType.VOLUME, VOLUME_HEADER),
			Tuple.of(MeasureType.WEIGHT, WEIGHT_HEADER));

	private static final Map<String, Set<CellType>> SUPPORTED_CELL_TYPES = Map.of(RESOURCE_TITLE_HEADER,
			Set.of(CellType.STRING), HEIGHT_HEADER, Set.of(CellType.NUMERIC), THICKNESS_HEADER,
			Set.of(CellType.NUMERIC), AREA_HEADER, Set.of(CellType.NUMERIC), VOLUME_HEADER, Set.of(CellType.NUMERIC),
			WEIGHT_HEADER, Set.of(CellType.NUMERIC));

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
			throw new InvalidBudgetDocumentException("First row must be Compilation Name");
		}

		String compilationName = compilationNameCell.getStringCellValue();

		Row headersRow = rowIterator.next();

		List<Header> headers = new ArrayList<>();

		Map<String, MeasureUnit> unitsOfMeasures = new HashMap<>();

		AtomicInteger index = new AtomicInteger(0);
		headersRow.forEach(cell -> {
			CellType cellType = cell.getCellType();
			if (cellType != CellType.STRING) {
				throw new InvalidBudgetDocumentException(
						String.format("Header cell type must be string, but was %s", cellType));
			}

			String header = cell.getStringCellValue().strip().toLowerCase();

			if (KNOWN_HEADERS.contains(header)) {
				headers.add(new Header(header, index.get()));
			}
			else {
				String foundHeader = KNOWN_MEASURE_HEADERS.stream()
						.map(Tuple2::_2)
						.filter(knownHeader -> header.toLowerCase().contains(knownHeader))
						.findFirst()
						.orElse(null);

				if (foundHeader != null) {
					String unitString = header.substring(header.indexOf('(')).strip().replaceAll("[()]", "");

					MeasureUnit measureUnit = MeasureUnit.fromString(unitString);

					headers.add(new Header(foundHeader, index.get()));
					unitsOfMeasures.put(foundHeader, measureUnit);
				}
			}
			index.incrementAndGet();
		});

		List<Map<String, Object>> data = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Map<String, Object> rowData = new HashMap<>();
			for (Header header : headers) {
				Cell cell = row.getCell(header.getIndex());
				if (cell == null) {
					rowData.put(header.getName(), null);
				}
				else {
					CellType cellType = cell.getCellType();

					Set<CellType> cellTypes = SUPPORTED_CELL_TYPES.get(header.getName());
					if (!cellTypes.contains(cellType)) {
						throw new 	InvalidBudgetDocumentException(
								String.format("Invalid cell type %s for header %s. Supported types are %s", cellType,
										header.name, cellTypes));
					}

					Object cellValue = getCellValue(cell);

					rowData.put(header.getName(), cellValue);
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

	private static final class Header {
		private final String name;
		private final int index;

		private Header(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public int getIndex() {
			return index;
		}
	}
}
