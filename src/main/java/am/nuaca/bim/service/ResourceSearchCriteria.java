package am.nuaca.bim.service;

import java.util.List;

import am.nuaca.bim.application.model.Measure;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class ResourceSearchCriteria {

	private final int compilationId;

	private final String resourceTitle;

	private final List<Measure> measures;

	public ResourceSearchCriteria(int compilationId, String resourceTitle, List<Measure> measures) {
		this.compilationId = compilationId;
		this.resourceTitle = resourceTitle;
		this.measures = measures;
	}

	public int getCompilationId() {
		return compilationId;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public List<Measure> getMeasures() {
		return measures;
	}
}
