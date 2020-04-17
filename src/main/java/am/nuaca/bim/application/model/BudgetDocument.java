package am.nuaca.bim.application.model;

import java.util.List;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class BudgetDocument {

	private final String compilationName;

	private final String resourceTitle;

	private final List<Measure> measures;

	public BudgetDocument(String compilationName, String resourceTitle, List<Measure> measures) {
		this.compilationName = compilationName;
		this.resourceTitle = resourceTitle;
		this.measures = measures;
	}

	public String getCompilationName() {
		return compilationName;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public List<Measure> getMeasures() {
		return measures;
	}
}
