package am.nuaca.bim.endpoint.api;

import am.nuaca.bim.dto.UserRegistrationCommand;
import am.nuaca.bim.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@RestController
@RequestMapping("api")
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
