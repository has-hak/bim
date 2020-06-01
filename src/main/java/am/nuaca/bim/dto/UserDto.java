package am.nuaca.bim.dto;

import java.util.Set;

import am.nuaca.bim.entity.Role;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
public class UserDto {

	private final Long id;

	private final String email;

	private final String name;

	private final Set<Role> roles;

	private final UserPreferencesDto userPreferences;

	public UserDto(Long id, String email, String name, Set<Role> roles, UserPreferencesDto userPreferences) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.roles = roles;
		this.userPreferences = userPreferences;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public UserPreferencesDto getUserPreferences() {
		return userPreferences;
	}
}
