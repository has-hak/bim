package am.nuaca.bim.endpoint.api;


import am.nuaca.bim.dto.UserPreferencesDto;
import am.nuaca.bim.dto.UserPreferencesUpdateCommand;
import am.nuaca.bim.entity.UserPreferences;
import am.nuaca.bim.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 01-Jun-20.
 */
@RestController
@RequestMapping("api/users/{userId}/preferences")
public class UserPreferencesController {

	private final UserService userService;

	public UserPreferencesController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public UserPreferencesDto getPreferences(@PathVariable("userId") long userId) {
		UserPreferences preferences = userService.getPreferences(userId);
		return new UserPreferencesDto(preferences.getLanguage().getId());
	}

	@PutMapping
	public void update(@PathVariable("userId") long userId, @RequestBody UserPreferencesUpdateCommand command) {
		userService.updatePreferences(userId, command);
	}
}
