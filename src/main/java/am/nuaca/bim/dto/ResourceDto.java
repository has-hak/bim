package am.nuaca.bim.dto;

import java.util.Map;

import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.entity.ResourceMeasureValue;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class ResourceDto {

	private final Long id;

	private final String code;

	private final String title;

	private final Map<MeasureType, ResourceMeasureValue> measures;

	public ResourceDto(Long id, String code, String title, Map<MeasureType, ResourceMeasureValue> measures) {
		this.id = id;
		this.code = code;
		this.title = title;
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

	public Map<MeasureType, ResourceMeasureValue> getMeasures() {
		return measures;
	}
}
