package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public class Measure {

	private final MeasureType type;

	private final MeasureUnit measureUnit;

	private final double value;

	public Measure(MeasureType type, MeasureUnit measureUnit, double value) {
		this.type = type;
		this.measureUnit = measureUnit;
		this.value = value;
	}

	public MeasureType getType() {
		return type;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public double getValue() {
		return value;
	}

	public Measure changeValue(MeasureUnit measureUnit, double value) {
		return new Measure(type, measureUnit, value);
	}
}
