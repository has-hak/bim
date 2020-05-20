package am.nuaca.bim.service;

import java.util.List;

import am.nuaca.bim.entity.User;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return Iterables.iterableToList(userRepository.findAll());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
}
