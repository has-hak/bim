package am.nuaca.bim.repository;

import am.nuaca.bim.entity.Resource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public interface ResourcesRepository extends CrudRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
}
