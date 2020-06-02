package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
public class UserPreferencesUpdateCommand {

	private final Integer languageId;

	@JsonCreator
	public UserPreferencesUpdateCommand(@JsonProperty("languageId") Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getLanguageId() {
		return languageId;
	}
}
