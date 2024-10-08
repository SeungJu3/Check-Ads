package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import project.FeedbackApplication;
import project.domain.FeedbackDeleted;
import project.domain.FeedbackModified;
import project.domain.FeedbackRegistered;

@Entity
@Table(name = "Feedback_table")
@Data
//<<< DDD / Aggregate Root
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long constId;

    private String content;

    private Long userId;

    @PostPersist
    public void onPostPersist() {
        FeedbackRegistered feedbackRegistered = new FeedbackRegistered(this);
        feedbackRegistered.publishAfterCommit();

        FeedbackModified feedbackModified = new FeedbackModified(this);
        feedbackModified.publishAfterCommit();

        FeedbackDeleted feedbackDeleted = new FeedbackDeleted(this);
        feedbackDeleted.publishAfterCommit();
    }

    public static FeedbackRepository repository() {
        FeedbackRepository feedbackRepository = FeedbackApplication.applicationContext.getBean(
            FeedbackRepository.class
        );
        return feedbackRepository;
    }
}
//>>> DDD / Aggregate Root
