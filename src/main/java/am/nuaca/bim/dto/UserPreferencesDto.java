package am.nuaca.bim.dto;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
public class UserPreferencesDto {

	private final Integer languageId;

	public UserPreferencesDto(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getLanguageId() {
		return languageId;
	}
}
