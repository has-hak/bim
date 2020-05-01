package am.nuaca.bim.application.document;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import am.nuaca.bim.application.Standards;
import am.nuaca.bim.application.model.MeasureUnit;
import am.nuaca.bim.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Tigran Sargsyan on 27-Apr-20.
 */
public class OutlayDocumentBuilder {

	private static final int CODE_SECTION_COLUMN = 0;
	private static final int TITLE_COLUMN = 3;
	private static final int MEASURE_UNIT_COLUMN = 8;
	private static final int UNIT_COLUMN = 9;
	private static final int UNIT_COST_COLUMN = 10;
	private static final int TOTAL_COST_COLUMN = 11;

	private final List<Resource> resources;

	private Sheet sheet;

	public OutlayDocumentBuilder(List<Resource> resources) {
		this.resources = resources;
	}

	public void build(OutputStream outputStream) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Նախահաշիվ");

		buildMainHeaders(sheet);

		int rowCursor = 1;

		for (Resource resource : resources) {
			List<Cell> totalCostCells = new ArrayList<>();
			Row resourceRow = sheet.createRow(rowCursor++);

			Cell resourceCostCell = buildResourceRow(resourceRow, resource);

			if (!resource.getWorkforces().isEmpty()) {
				Row workforcesHeaderRow = sheet.createRow(rowCursor++);
				buildWorkforcesHeader(workforcesHeaderRow);
				List<Workforce> workforces = resource.getWorkforces();
				for (Workforce workforce : workforces) {
					Row workforceRow = sheet.createRow(rowCursor++);
					totalCostCells.add(buildWorkforceRow(workforceRow, workforce));
				}
			}

			if (!resource.getMachines().isEmpty()) {
				Row machinesHeaderRow = sheet.createRow(rowCursor++);
				buildMachinesHeader(machinesHeaderRow);

				for (Machine machine : resource.getMachines()) {
					Row machineRow = sheet.createRow(rowCursor++);
					totalCostCells.add(buildMachineRow(machineRow, machine));
				}
			}

			if (!resource.getMaterials().isEmpty()) {
				Row materialsHeaderRow = sheet.createRow(rowCursor++);
				buildMaterialsHeader(materialsHeaderRow);

				for (Material material : resource.getMaterials()) {
					Row materialRow = sheet.createRow(rowCursor++);
					totalCostCells.add(buildMaterialRow(materialRow, material));
				}
			}

			Row directCostRow = sheet.createRow(rowCursor++);
			Cell directCostCell = buildDirectCostRow(directCostRow, totalCostCells);

			Row overheadCostRow = sheet.createRow(rowCursor++);
			Cell overheadCostCell = buildOverheadCostRow(overheadCostRow, directCostCell);

			Row netCostRow = sheet.createRow(rowCursor++);
			Cell netCostCell = buildNetCostRow(netCostRow, directCostCell, overheadCostCell);

			Row profitRow = sheet.createRow(rowCursor++);
			Cell profitCell = buildProfitRow(profitRow, netCostCell);

			Row outlayRow = sheet.createRow(rowCursor++);
			Cell outlayCell = buildOutlayRow(outlayRow, netCostCell, profitCell);

			resourceCostCell.setCellFormula(outlayCell.getAddress().formatAsString());

			rowCursor++;
		}

		try {
			workbook.write(outputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buildMainHeaders(Sheet sheet) {
		Row row = sheet.createRow(0);
		Cell codeHeaderCell = buildHeaderCell(row, CODE_SECTION_COLUMN);
		codeHeaderCell.setCellValue("Ծածկագիր");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, CODE_SECTION_COLUMN, CODE_SECTION_COLUMN + 2));

		Cell titleHeaderCell = buildHeaderCell(row, TITLE_COLUMN);
		titleHeaderCell.setCellValue("Աշխատանքի անվանումը");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, TITLE_COLUMN, TITLE_COLUMN + 4));

		Cell measureUnitHeaderCell = buildHeaderCell(row, MEASURE_UNIT_COLUMN);
		measureUnitHeaderCell.setCellValue("Չ․մ․");

		Cell unitHeaderCell = buildHeaderCell(row, UNIT_COLUMN);
		unitHeaderCell.setCellValue("Ծավալը");

		Cell unitCostHeaderCell = buildHeaderCell(row, UNIT_COST_COLUMN);
		unitCostHeaderCell.setCellValue("Միավորի արժեքը");

		Cell totalCostHeaderCell = buildHeaderCell(row, TOTAL_COST_COLUMN);
		totalCostHeaderCell.setCellValue("Ընդհանուր արժեքը");
	}

	private Cell buildHeaderCell(Row row, int column) {
		Cell cell = createCell(row, column);
		CellStyle cellStyle = cell.getCellStyle();
		Font font = sheet.getWorkbook().createFont();
		short fontSize = 14 * 20;
		font.setFontHeight(fontSize);
		font.setBold(true);
		cellStyle.setFont(font);
		sheet.setColumnWidth(column, 6000);
		cell.setCellStyle(cellStyle);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cell;
	}

	private Cell buildResourceRow(Row resourceRow, Resource resource) {
		buildCodeSection(resourceRow, resource.getCode());

		Cell titleCell = buildTitleCell(resourceRow, resource.getTitle());

		CellStyle cellStyle = titleCell.getCellStyle();
		Font font = sheet.getWorkbook().createFont();
		short fontSize = 13 * 20;
		font.setFontHeight(fontSize);
		font.setBold(true);
		cellStyle.setFont(font);
		titleCell.setCellStyle(cellStyle);
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		buildMeasureUnitCell(resourceRow, MeasureUnit.M3);
		Cell unitCell = buildUnitCell(resourceRow, 1);
		Cell unitCostCell = buildUnitCostCell(resourceRow, -1);
		buildTotalCostCell(resourceRow, unitCell, unitCostCell);
		return unitCostCell;
	}

	private void buildWorkforcesHeader(Row row) {
		Cell cell = createCell(row, TITLE_COLUMN);
		cell.setCellValue("Աշխատուժ");
		applyGroupRowTitleStyles(cell);
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), TITLE_COLUMN, TITLE_COLUMN + 4));
	}

	private Cell buildWorkforceRow(Row workforceRow, Workforce workforce) {
		buildTitleCell(workforceRow, workforce.getTitle());
		buildMeasureUnitCell(workforceRow, MeasureUnit.HUMAN_HOUR);
		Cell unitCell = buildUnitCell(workforceRow, workforce.getUnit());
		Cell unitCostCell = buildUnitCostCell(workforceRow, workforce.getUnitCost());
		return buildTotalCostCell(workforceRow, unitCell, unitCostCell);
	}

	private void buildMachinesHeader(Row row) {
		Cell cell = createCell(row, TITLE_COLUMN);
		cell.setCellValue("Մեքենաներ և մեխանիզմներ");
		applyGroupRowTitleStyles(cell);
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), TITLE_COLUMN, TITLE_COLUMN + 4));
	}

	private Cell buildMachineRow(Row machineRow, Machine machine) {
		buildCodeSection(machineRow, machine.getCode());
		buildTitleCell(machineRow, machine.getTitle());
		buildMeasureUnitCell(machineRow, MeasureUnit.MACHINE_HOUR);
		Cell unitCell = buildUnitCell(machineRow, machine.getUnit());
		Cell unitCostCell = buildUnitCostCell(machineRow, machine.getUnitCost());
		return buildTotalCostCell(machineRow, unitCell, unitCostCell);
	}

	private void buildMaterialsHeader(Row row) {
		Cell cell = createCell(row, TITLE_COLUMN);
		cell.setCellValue("Նյութեր");
		applyGroupRowTitleStyles(cell);
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), TITLE_COLUMN, TITLE_COLUMN + 4));
	}

	private Cell buildMaterialRow(Row machineRow, Material material) {
		buildCodeSection(machineRow, material.getCode());
		buildTitleCell(machineRow, material.getTitle());
		buildMeasureUnitCell(machineRow, Standards.MEASURE_UNITS.get(material.getMeasureType()));
		Cell unitCell = buildUnitCell(machineRow, material.getUnit());
		Cell unitCostCell = buildUnitCostCell(machineRow, material.getUnitCost());
		return buildTotalCostCell(machineRow, unitCell, unitCostCell);
	}

	private Cell buildDirectCostRow(Row row, List<Cell> totalCostCells) {
		Cell titleCell = buildTitleCell(row, "Ընդամենը ուղղակի ծախսեր");
		applyResultRowTitleStyles(titleCell);

		Cell unitCostCell = createCell(row, UNIT_COST_COLUMN);
		Cell totalCostCell = createCell(row, TOTAL_COST_COLUMN);
		applyResultRowCostStyles(totalCostCell);

		if (totalCostCells.isEmpty()) {
			unitCostCell.setCellValue(0);
			totalCostCell.setCellValue(0);
			return totalCostCell;
		}

		StringJoiner formulaBuilder = new StringJoiner("+");
		for (Cell cell : totalCostCells) {
			String cellAddress = cell.getAddress().formatAsString();
			formulaBuilder.add(cellAddress);
		}

		String formula = formulaBuilder.toString();

		unitCostCell.setCellFormula(formula);

		totalCostCell.setCellFormula(unitCostCell.getAddress().formatAsString());

		return totalCostCell;
	}

	private Cell buildOverheadCostRow(Row row, Cell directCostCell) {
		double overheadCostPercentage = Standards.OVERHEAD_COST_PERCENTAGE;
		Cell titleCell = buildTitleCell(row, String.format("Վերադիր ծախսեր %s %%", overheadCostPercentage));
		applyResultRowTitleStyles(titleCell);

		String formula = directCostCell.getAddress().formatAsString() + "*" + (overheadCostPercentage / 100);

		Cell unitCostCell = createCell(row, UNIT_COST_COLUMN);
		Cell totalCostCell = createCell(row, TOTAL_COST_COLUMN);
		applyResultRowCostStyles(totalCostCell);

		unitCostCell.setCellFormula(formula);

		totalCostCell.setCellFormula(unitCostCell.getAddress().formatAsString());

		return totalCostCell;
	}

	private Cell buildNetCostRow(Row row, Cell directCostCell, Cell overheadCostCell) {
		Cell titleCell = buildTitleCell(row, "Ինքնարժեք");
		applyResultRowTitleStyles(titleCell);

		String formula = directCostCell.getAddress().formatAsString() + "+" + overheadCostCell.getAddress()
				.formatAsString();

		Cell unitCostCell = createCell(row, UNIT_COST_COLUMN);
		Cell totalCostCell = createCell(row, TOTAL_COST_COLUMN);
		applyResultRowCostStyles(totalCostCell);

		unitCostCell.setCellFormula(formula);

		totalCostCell.setCellFormula(unitCostCell.getAddress().formatAsString());

		return totalCostCell;
	}

	private Cell buildProfitRow(Row row, Cell netCostCell) {
		double profitPercentage = Standards.PROFIT_PERCENTAGE;
		Cell titleCell = buildTitleCell(row, String.format("Շահույթ %s %%", profitPercentage));
		applyResultRowTitleStyles(titleCell);

		String formula = netCostCell.getAddress().formatAsString() + "*" + (profitPercentage / 100);

		Cell unitCostCell = createCell(row, UNIT_COST_COLUMN);
		Cell totalCostCell = createCell(row, TOTAL_COST_COLUMN);
		applyResultRowCostStyles(totalCostCell);

		unitCostCell.setCellFormula(formula);

		totalCostCell.setCellFormula(unitCostCell.getAddress().formatAsString());

		return totalCostCell;
	}

	private Cell buildOutlayRow(Row row, Cell netCostCell, Cell profitCell) {
		Cell titleCell = buildTitleCell(row, "Ընդամենը նախահաշվային արժեք");
		applyResultRowTitleStyles(titleCell);

		String formula = netCostCell.getAddress().formatAsString() + "+" + profitCell.getAddress().formatAsString();

		Cell unitCostCell = createCell(row, UNIT_COST_COLUMN);
		Cell totalCostCell = createCell(row, TOTAL_COST_COLUMN);
		applyResultRowCostStyles(totalCostCell);

		unitCostCell.setCellFormula(formula);

		totalCostCell.setCellFormula(unitCostCell.getAddress().formatAsString());

		return totalCostCell;
	}

	private void buildCodeSection(Row row, Code code) {
		Cell cell = createCell(row, CODE_SECTION_COLUMN);
		cell.setCellValue(code.getPart1());
		Cell cell2 = createCell(row, CODE_SECTION_COLUMN + 1);
		cell2.setCellValue(code.getPart2());
		Cell cell3 = createCell(row, CODE_SECTION_COLUMN + 2);
		cell3.setCellValue(code.getPart3());
	}

	private Cell buildTitleCell(Row row, String title) {
		Cell cell = createCell(row, TITLE_COLUMN);
		cell.setCellValue(title);
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), TITLE_COLUMN, TITLE_COLUMN + 4));
		return cell;
	}

	private void buildMeasureUnitCell(Row row, MeasureUnit measureUnit) {
		Cell cell = createCell(row, MEASURE_UNIT_COLUMN);
		cell.setCellValue(measureUnit.getLocalizedName());
	}

	private Cell buildUnitCell(Row row, double unit) {
		Cell cell = createCell(row, UNIT_COLUMN);
		cell.setCellValue(unit);
		return cell;
	}

	private Cell buildUnitCostCell(Row row, double unitCost) {
		Cell cell = createCell(row, UNIT_COST_COLUMN);
		cell.setCellValue(unitCost);

		return cell;
	}

	private Cell buildTotalCostCell(Row row, Cell unitCell, Cell unitCostCell) {
		Cell cell = createCell(row, TOTAL_COST_COLUMN);
		cell.setCellFormula(
				(unitCell.getAddress().formatAsString()) + "*" + unitCostCell.getAddress().formatAsString());
		return cell;
	}

	private Cell createCell(Row row, int column) {
		Cell cell = row.createCell(column);
		applyDefaultStylesToCell(cell);
		return cell;
	}

	private void applyDefaultStylesToCell(Cell cell) {
		CellStyle cellStyle = createEmptyCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(cellStyle);
	}

	private CellStyle createEmptyCellStyle() {
		return sheet.getWorkbook().createCellStyle();
	}

	private void applyGroupRowTitleStyles(Cell cell) {
		CellStyle cellStyle = cell.getCellStyle();
		Font font = sheet.getWorkbook().createFont();
		short fontSize = 12 * 20;
		font.setFontHeight(fontSize);
		font.setBold(true);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
		cellStyle.setFillForegroundColor(IndexedColors.AQUA.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}

	private void applyResultRowTitleStyles(Cell cell) {
		CellStyle cellStyle = cell.getCellStyle();
		Font font = sheet.getWorkbook().createFont();
		short fontSize = 12 * 20;
		font.setFontHeight(fontSize);
		font.setBold(true);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}


	private void applyResultRowCostStyles(Cell cell) {
		CellStyle cellStyle = cell.getCellStyle();
		Font font = sheet.getWorkbook().createFont();
		short fontSize = 11 * 20;
		font.setFontHeight(fontSize);
		font.setBold(true);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}
}
