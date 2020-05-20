package am.nuaca.bim.repository;

import am.nuaca.bim.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String username);
}
