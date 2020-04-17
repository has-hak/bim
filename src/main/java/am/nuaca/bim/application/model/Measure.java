package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public class Measure {

	private final MeasureType type;

	private final Unit unit;

	private final double value;

	public Measure(MeasureType type, Unit unit, double value) {
		this.type = type;
		this.unit = unit;
		this.value = value;
	}

	public MeasureType getType() {
		return type;
	}

	public Unit getUnit() {
		return unit;
	}

	public double getValue() {
		return value;
	}

	public Measure changeValue(Unit unit, double value) {
		return new Measure(type, unit, value);
	}
}
