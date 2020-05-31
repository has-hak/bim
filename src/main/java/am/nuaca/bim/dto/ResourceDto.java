package am.nuaca.bim.dto;

import java.util.List;
import java.util.Map;

import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.entity.ResourceMeasureValue;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class ResourceDto {

	private final int compilationId;

	private final long id;

	private final String code;

	private final String title;

	private final Map<MeasureType, ResourceMeasureValue> measures;

	private final List<Long> workforceIds;

	private final List<Long> machineIds;

	private final List<Long> materialIds;

	public ResourceDto(int compilationId, long id, String code, String title,
					   Map<MeasureType, ResourceMeasureValue> measures, List<Long> workforceIds, List<Long> machineIds,
					   List<Long> materialIds) {
		this.compilationId = compilationId;
		this.id = id;
		this.code = code;
		this.title = title;
		this.measures = measures;
		this.workforceIds = workforceIds;
		this.machineIds = machineIds;
		this.materialIds = materialIds;
	}

	public int getCompilationId() {
		return compilationId;
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public Map<MeasureType, ResourceMeasureValue> getMeasures() {
		return measures;
	}

	public List<Long> getWorkforceIds() {
		return workforceIds;
	}

	public List<Long> getMachineIds() {
		return machineIds;
	}

	public List<Long> getMaterialIds() {
		return materialIds;
	}
}
