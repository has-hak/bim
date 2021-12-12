package am.nuaca.bim.repository;

import am.nuaca.bim.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String username);
}
