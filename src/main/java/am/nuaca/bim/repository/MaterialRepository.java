package am.nuaca.bim.repository;

import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.entity.Material;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Ani Khachatryan on 07-May-20.
 */
public interface MaterialRepository extends CrudRepository<Material, Long> {

    @Query(value = "SELECT * FROM materials WHERE MATCH(title) AGAINST(:text IN BOOLEAN MODE)", nativeQuery = true)
    List<Material> searchByTitlePattern(@Param("text") String text);
}
