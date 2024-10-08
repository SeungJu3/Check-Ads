package project.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportDeleted extends AbstractEvent {

    private Long id;
    private Long constId;
    private String content;
    private Long managerId;

    public ReportDeleted(Report aggregate) {
        super(aggregate);
    }

    public ReportDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
