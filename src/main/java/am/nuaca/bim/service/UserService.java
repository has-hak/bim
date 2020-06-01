package am.nuaca.bim.service;

import java.util.List;

import am.nuaca.bim.dto.UserPreferencesUpdateCommand;
import am.nuaca.bim.entity.Language;
import am.nuaca.bim.entity.User;
import am.nuaca.bim.entity.UserPreferences;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.LanguageRepository;
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

	private final LanguageRepository languageRepository;

	public UserService(UserRepository userRepository, LanguageRepository languageRepository) {
		this.userRepository = userRepository;
		this.languageRepository = languageRepository;
	}

	public List<User> getAllUsers() {
		return Iterables.iterableToList(userRepository.findAll());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	public UserPreferences getPreferences(long userId) {
		return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new).getUserPreferences();
	}

	public void updatePreferences(long userId, UserPreferencesUpdateCommand command) {
		User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
		Language language = languageRepository.findById(command.getLanguageId())
				.orElseThrow(IllegalArgumentException::new);
		user.setUserPreferences(new UserPreferences(language));
		userRepository.save(user);
	}
}
