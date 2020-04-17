package am.nuaca.bim.dto;

import java.util.Map;

import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.entity.ResourceMeasureValue;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class ResourceDto {

	private Long id;

	private String code;

	private String title;

	private String unit;

	private double unitCost;

	private Map<MeasureType, ResourceMeasureValue> measures;

	public ResourceDto(Long id, String code, String title, String unit, double unitCost,
					   Map<MeasureType, ResourceMeasureValue> measures) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
		this.measures = measures;
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

	public String getUnit() {
		return unit;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public Map<MeasureType, ResourceMeasureValue> getMeasures() {
		return measures;
	}
}
