package am.nuaca.bim.endpoint.api;

import java.security.Principal;
import java.util.List;

import am.nuaca.bim.dto.UserDto;
import am.nuaca.bim.dto.UserPreferencesDto;
import am.nuaca.bim.entity.User;
import am.nuaca.bim.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@Secured("ADMIN")
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@GetMapping("/me")
	public UserDto me(Principal principal) {
		User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getRoles(),
				new UserPreferencesDto(user.getUserPreferences().getLanguage().getId()));
	}
}
