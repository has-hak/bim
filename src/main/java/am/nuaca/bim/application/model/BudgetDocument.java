package am.nuaca.bim.application.model;

import java.util.List;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public class BudgetDocument {

	private final String compilationName;

	private final List<BudgetDocumentResource> resources;

	public BudgetDocument(String compilationName, List<BudgetDocumentResource> resources) {
		this.compilationName = compilationName;
		this.resources = resources;
	}

	public String getCompilationName() {
		return compilationName;
	}

	public List<BudgetDocumentResource> getResources() {
		return resources;
	}
}
