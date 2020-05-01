package am.nuaca.bim.service;

import java.util.List;

import am.nuaca.bim.application.model.Measure;

/**
 * @author Tigran Sargsyan on 01-May-20.
 */
public class ResourceAttributes {

	private final String title;

	private final List<Measure> measures;

	public ResourceAttributes(String title, List<Measure> measures) {
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
