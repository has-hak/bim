package am.nuaca.bim.service;

import java.security.Principal;
import java.util.List;

import am.nuaca.bim.dto.UserPreferencesUpdateCommand;
import am.nuaca.bim.entity.Language;
import am.nuaca.bim.entity.User;
import am.nuaca.bim.entity.UserPreferences;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.LanguageRepository;
import am.nuaca.bim.repository.UserPreferencesRepository;
import am.nuaca.bim.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

	private final UserPreferencesRepository userPreferencesRepository;

	public UserService(UserRepository userRepository, LanguageRepository languageRepository,
					   UserPreferencesRepository userPreferencesRepository) {
		this.userRepository = userRepository;
		this.languageRepository = languageRepository;
		this.userPreferencesRepository = userPreferencesRepository;
	}

	public List<User> getAllUsers() {
		return Iterables.iterableToList(userRepository.findAll());
	}

	public User getUserById(long userId) {
		return userRepository.findById(userId).orElseThrow(RuntimeException::new);
	}

	public User getUserFromPrincipal(Principal principal) {
		Long userId = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
		return getUserById(userId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	public UserPreferences getPreferences(long userId) {
		return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new).getPreferences();
	}

	public void updatePreferences(long userId, UserPreferencesUpdateCommand command) {
		User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

		UserPreferences userPreferences = user.getPreferences();
		if (userPreferences.getId() == null) {
			userPreferences = userPreferencesRepository.save(userPreferences);
			user.setPreferences(userPreferences);
		}

		if (command.getLanguageId() != null) {
			Language language = languageRepository.findById(command.getLanguageId())
					.orElseThrow(IllegalArgumentException::new);
			userPreferences.setLanguage(language);
		}

		userRepository.save(user);
	}
}
