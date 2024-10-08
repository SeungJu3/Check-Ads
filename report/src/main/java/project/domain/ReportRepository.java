package project.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "reports", path = "reports")
public interface ReportRepository
    extends PagingAndSortingRepository<Report, Long> {
    @Query(
        value = "select report " +
        "from Report report " +
        "where(:constId is null or report.constId = :constId)"
    )
    List<Report> findByReadReportList(Long constId, Pageable pageable);
}
