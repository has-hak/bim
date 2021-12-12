package am.nuaca.bim.repository;

import am.nuaca.bim.entity.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ani Khachatryan on 23-May-20.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {}
