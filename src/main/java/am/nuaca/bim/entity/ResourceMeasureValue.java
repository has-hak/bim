package am.nuaca.bim.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ani Khachatryan on 16-Apr-20.
 */
public class ResourceMeasureValue implements Serializable {

	private final double startValue;

	private final double endValue;

	@JsonCreator
	public ResourceMeasureValue(@JsonProperty("startValue") double startValue,
								@JsonProperty("endValue") double endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
	}

	public double getStartValue() {
		return startValue;
	}

	public double getEndValue() {
		return endValue;
	}
}
