package project.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class FeedbackDeleted extends AbstractEvent {

    private Long id;
    private Long constId;
    private String content;
    private Long userId;

    public FeedbackDeleted(Feedback aggregate) {
        super(aggregate);
    }

    public FeedbackDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
