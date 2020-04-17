package am.nuaca.bim.service;

import am.nuaca.bim.dto.UserRegistrationCommand;
import am.nuaca.bim.entity.User;
import am.nuaca.bim.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Service
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerNewUser(UserRegistrationCommand userRegistrationCommand) {
		String rawPassword = userRegistrationCommand.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		User user = new User(userRegistrationCommand.getName(), userRegistrationCommand.getEmail(),
				userRegistrationCommand.getUsername(), encodedPassword);
		userRepository.save(user);
	}
}
