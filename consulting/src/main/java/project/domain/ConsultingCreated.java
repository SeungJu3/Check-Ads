package project.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultingCreated extends AbstractEvent {

    private Long id;
    private Date date;
    private Long resId;
    private Long managerId;
    private Long feedbackId;
    private Long reportId;

    public ConsultingCreated(Consulting aggregate) {
        super(aggregate);
    }

    public ConsultingCreated() {
        super();
    }
}
//>>> DDD / Domain Event
