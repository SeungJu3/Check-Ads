package project.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class FeedbackRegistered extends AbstractEvent {

    private Long id;
    private Long constId;
    private String content;
    private Long userId;

    public FeedbackRegistered(Feedback aggregate) {
        super(aggregate);
    }

    public FeedbackRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
