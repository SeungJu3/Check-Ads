package project.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "feedbacks", path = "feedbacks")
public interface FeedbackRepository
    extends PagingAndSortingRepository<Feedback, Long> {
    @Query(
        value = "select feedback " +
        "from Feedback feedback " +
        "where(:constId is null or feedback.constId = :constId)"
    )
    List<Feedback> findByReadFeedbackList(Long constId, Pageable pageable);
}
