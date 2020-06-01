package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
public class UserPreferencesUpdateCommand {

	private final long languageId;

	@JsonCreator
	public UserPreferencesUpdateCommand(@JsonProperty("languageId") long languageId) {
		this.languageId = languageId;
	}

	public long getLanguageId() {
		return languageId;
	}
}
