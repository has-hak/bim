package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class ResourceCreationCommand {

	private final int compilationId;

	private final String code;

	private final String title;

	@JsonCreator
	public ResourceCreationCommand(@JsonProperty("compilationId") int compilationId, @JsonProperty("code") String code,
								   @JsonProperty("title") String title) {
		this.compilationId = compilationId;
		this.code = code;
		this.title = title;
	}

	public int getCompilationId() {
		return compilationId;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
}
