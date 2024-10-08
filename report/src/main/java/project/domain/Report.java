package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import project.ReportApplication;
import project.domain.ReportDeleted;
import project.domain.ReportModified;
import project.domain.ReportRegistered;

@Entity
@Table(name = "Report_table")
@Data
//<<< DDD / Aggregate Root
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long constId;

    private String content;

    private Long managerId;

    @PostPersist
    public void onPostPersist() {
        ReportDeleted reportDeleted = new ReportDeleted(this);
        reportDeleted.publishAfterCommit();

        ReportModified reportModified = new ReportModified(this);
        reportModified.publishAfterCommit();

        ReportRegistered reportRegistered = new ReportRegistered(this);
        reportRegistered.publishAfterCommit();
    }

    public static ReportRepository repository() {
        ReportRepository reportRepository = ReportApplication.applicationContext.getBean(
            ReportRepository.class
        );
        return reportRepository;
    }
}
//>>> DDD / Aggregate Root
