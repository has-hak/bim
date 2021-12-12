package am.nuaca.bim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public class WorkforceCreationCommand {

	@NotBlank(message = "validation.code.not-blank")
	@NotNull(message = "validation.code.not-null")
	private final String code;

	@NotBlank(message = "validation.title.not-blank")
	@NotNull(message = "validation.title.not-null")
	private final String title;

	@NotNull(message = "validation.unit.not-null")
	private final Double unit;

	@NotNull(message = "validation.unitCost.not-null")
	private final Double unitCost;

	@JsonCreator
	public WorkforceCreationCommand(@JsonProperty("code") String code, @JsonProperty("title") String title,
									@JsonProperty("unit") Double unit, @JsonProperty("unitCost") Double unitCost) {
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

	public Double getUnit() {
		return unit;
	}

	public Double getUnitCost() {
		return unitCost;
	}
}
