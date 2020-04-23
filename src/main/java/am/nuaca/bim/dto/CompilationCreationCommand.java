package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class CompilationCreationCommand {

	private String title;

	@JsonCreator
	public CompilationCreationCommand(@JsonProperty("title") String title) {
		this.title = title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
