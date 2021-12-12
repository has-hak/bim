package am.nuaca.bim.application.model;

import java.util.List;

/**
 * @author Ani Khachatryan on 01-May-20.
 */
public class BudgetDocumentResource {

	private final String title;

	private final List<Measure> measures;

	public BudgetDocumentResource(String title, List<Measure> measures) {
		this.title = title;
		this.measures = measures;
	}

	public String getTitle() {
		return title;
	}

	public List<Measure> getMeasures() {
		return measures;
	}
}
