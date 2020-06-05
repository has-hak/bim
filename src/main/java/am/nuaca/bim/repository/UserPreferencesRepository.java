package am.nuaca.bim.repository;

import am.nuaca.bim.entity.UserPreferences;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tigran Sargsyan on 05-Jun-20.
 */
public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {

	UserPreferences findByUserId(long userId);
}
