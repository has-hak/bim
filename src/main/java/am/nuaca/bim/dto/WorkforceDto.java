package am.nuaca.bim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import am.nuaca.bim.application.model.MeasureType;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class WorkforceDto {

	private final Long id;

	private final String code;

	private final String title;

	private final double unit;

	private final double unitCost;

	public WorkforceDto(Long id, String code, String title, double unit, double unitCost) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
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
}
