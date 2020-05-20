package am.nuaca.bim.dto;

import am.nuaca.bim.application.model.MeasureType;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class MaterialDto {

	private final Long id;

	private final String code;

	private final String title;

	private final double unit;

	private final double unitCost;

	private final MeasureType measureType;

	public MaterialDto(Long id, String code, String title, double unit, double unitCost, MeasureType measureType) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
		this.measureType = measureType;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public double getUnit() {
		return unit;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public MeasureType getMeasureType() {
		return measureType;
	}
}
