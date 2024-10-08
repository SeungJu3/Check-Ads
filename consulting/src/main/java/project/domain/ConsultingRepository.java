package project.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "consultings",
    path = "consultings"
)
public interface ConsultingRepository
    extends PagingAndSortingRepository<Consulting, Long> {
    @Query(
        value = "select consulting " +
        "from Consulting consulting " +
        "where(:userId is null or consulting.userId = :userId) and (:resId is null or consulting.resId = :resId)"
    )
    List<Consulting> findByReadConsultingList(
        Long userId,
        Long resId,
        Pageable pageable
    );
}
