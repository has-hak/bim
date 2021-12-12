package am.nuaca.bim.repository;

import java.util.List;

import am.nuaca.bim.entity.Compilation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
public interface CompilationRepository extends CrudRepository<Compilation, Integer> {

	@Query(value = "SELECT * FROM compilations WHERE MATCH(title) AGAINST(:text IN BOOLEAN MODE)", nativeQuery = true)
	List<Compilation> searchByTitlePattern(@Param("text") String text);
}
