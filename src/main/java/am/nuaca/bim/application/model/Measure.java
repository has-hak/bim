package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public class Measure {

	private final Unit unit;

	private final double value;

	public Measure(Unit unit, double value) {
		this.unit = unit;
		this.value = value;
	}

	public Unit getUnit() {
		return unit;
	}

	public double getValue() {
		return value;
	}
}
