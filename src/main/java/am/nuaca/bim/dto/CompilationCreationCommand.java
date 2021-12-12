package am.nuaca.bim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public class CompilationCreationCommand {

	@NotBlank(message = "validation.title.not-blank")
	@NotNull(message = "validation.title.not-null")
	private final String title;

	@JsonCreator
	public CompilationCreationCommand(@JsonProperty("title") String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
