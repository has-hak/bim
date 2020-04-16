package am.nuaca.bim.security;

import java.util.Collection;

import am.nuaca.bim.entity.User;
import am.nuaca.bim.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	public AuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		User user = (User) userService.loadUserByUsername(username);

		if (user != null && user.getUsername().equals(username)) {
			if (!passwordEncoder.matches(password, user.getPassword())) {
				throw new BadCredentialsException("Wrong password");
			}

			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		}
		else
			throw new BadCredentialsException("Username not found");
	}

	public boolean supports(Class<?> arg) {
		return true;
	}
}
