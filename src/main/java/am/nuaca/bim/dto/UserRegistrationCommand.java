package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class UserRegistrationCommand {

	private final String fullName;

	private final String email;

	private final String password;

	@JsonCreator
	public UserRegistrationCommand(@JsonProperty("fullName") String fullName, @JsonProperty("email") String email,
								   @JsonProperty("password") String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
