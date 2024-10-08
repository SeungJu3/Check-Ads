package project.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationCanceled extends AbstractEvent {

    private Long id;
    private String request;
    private String product;
    private Integer prodCnt;
    private String category;
    private String subCategory;
    private Date date;
    private Boolean status;
    private Long userId;

    public ReservationCanceled(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
