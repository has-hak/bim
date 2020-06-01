package am.nuaca.bim.dto;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
public class UserPreferencesDto {

	private final long languageId;

	public UserPreferencesDto(long languageId) {
		this.languageId = languageId;
	}

	public long getLanguageId() {
		return languageId;
	}
}
