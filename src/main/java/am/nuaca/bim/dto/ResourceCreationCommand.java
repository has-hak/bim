package am.nuaca.bim.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class ResourceCreationCommand {

	private final int compilationId;

	private final String code;

	private final String title;

	private final List<Long> machineIds;

	private final List<Long> workforceIds;

	private final List<Long> materialIds;

	@JsonCreator
	public ResourceCreationCommand(@JsonProperty("compilationId") int compilationId, @JsonProperty("code") String code,
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

	public int getCompilationId() {
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
