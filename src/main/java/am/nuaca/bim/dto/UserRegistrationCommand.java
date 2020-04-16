package am.nuaca.bim.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class UserRegistrationCommand {

	private final String name;

	private final String username;

	private final String password;

	@JsonCreator
	public UserRegistrationCommand(@JsonProperty("name") String name, @JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
