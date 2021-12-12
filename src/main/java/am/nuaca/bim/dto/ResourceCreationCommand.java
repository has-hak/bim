package am.nuaca.bim.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public class ResourceCreationCommand {

	@NotNull(message = "validation.compilationId.not-null")
	private final Integer compilationId;

	@NotBlank(message = "validation.code.not-blank")
	@NotNull(message = "validation.code.not-null")
	private final String code;

	@NotBlank(message = "validation.title.not-blank")
	@NotNull(message = "validation.title.not-null")
	private final String title;

	private final List<Long> machineIds;

	private final List<Long> workforceIds;

	private final List<Long> materialIds;

	@JsonCreator
	public ResourceCreationCommand(@JsonProperty("compilationId") Integer compilationId, @JsonProperty("code") String code,
								   @JsonProperty("title") String title,
								   @JsonProperty("machineIds") List<Long> machineIds,
								   @JsonProperty("workforceIds") List<Long> workforceIds,
								   @JsonProperty("materialIds") List<Long> materialIds) {
		this.compilationId = compilationId;
		this.code = code;
		this.title = title;
		this.machineIds = machineIds;
		this.workforceIds = workforceIds;
		this.materialIds = materialIds;
	}

	public Integer getCompilationId() {
		return compilationId;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public List<Long> getMachineIds() {
		return machineIds;
	}

	public List<Long> getWorkforceIds() {
		return workforceIds;
	}

	public List<Long> getMaterialIds() {
		return materialIds;
	}
}
