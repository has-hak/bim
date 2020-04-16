package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class BudgetDocument {

	private final String compilationName;

	private final Measure height;

	private final Measure thickness;

	private final Measure area;

	private final Measure volume;

	public BudgetDocument(String compilationName, Measure height, Measure thickness, Measure area, Measure volume) {
		this.compilationName = compilationName;
		this.height = height;
		this.thickness = thickness;
		this.area = area;
		this.volume = volume;
	}

	public String getCompilationName() {
		return compilationName;
	}

	public Measure getHeight() {
		return height;
	}

	public Measure getThickness() {
		return thickness;
	}

	public Measure getArea() {
		return area;
	}

	public Measure getVolume() {
		return volume;
	}
}
