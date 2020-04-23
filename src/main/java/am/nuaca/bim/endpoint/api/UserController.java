package am.nuaca.bim.endpoint.api;

import java.util.List;

import am.nuaca.bim.entity.User;
import am.nuaca.bim.service.UserService;
import org.springframework.security.access.annotation.Secured;
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
}
