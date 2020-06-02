package am.nuaca.bim.endpoint.api;


import java.security.Principal;

import am.nuaca.bim.dto.UserPreferencesDto;
import am.nuaca.bim.dto.UserPreferencesUpdateCommand;
import am.nuaca.bim.entity.User;
import am.nuaca.bim.entity.UserPreferences;
import am.nuaca.bim.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
@RestController
@RequestMapping("api/users/me/preferences")
public class UserPreferencesController {

	private final UserService userService;

	public UserPreferencesController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public UserPreferencesDto getPreferences(Principal principal) {
		User user = userService.getUserFromPrincipal(principal);
		UserPreferences preferences = user.getPreferences();
		return new UserPreferencesDto(preferences.getLanguage() == null ? null : preferences.getLanguage().getId());
	}

	@PutMapping
	public void update(Principal principal, @RequestBody UserPreferencesUpdateCommand command) {
		User user = userService.getUserFromPrincipal(principal);
		userService.updatePreferences(user.getId(), command);
	}
}
