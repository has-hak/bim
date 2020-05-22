package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class WorkforceCreationCommand {

	private final String code;

	private final String title;

	private final double unit;

	private final double unitCost;

	@JsonCreator
	public WorkforceCreationCommand(@JsonProperty("code") String code, @JsonProperty("title") String title,
									@JsonProperty("unit") double unit, @JsonProperty("unitCost") double unitCost) {
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
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
