package am.nuaca.bim.endpoint;

import am.nuaca.bim.dto.UserRegistrationCommand;
import am.nuaca.bim.service.AuthService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/registration", consumes = "application/json")
	public void register(@RequestBody UserRegistrationCommand userRegistrationCommand) {
		authService.registerNewUser(userRegistrationCommand);
	}
}
