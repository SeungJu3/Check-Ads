package project.domain;

import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

@Data
@ToString
public class ReservationModified extends AbstractEvent {

    private Long id;
    private String request;
    private String product;
    private Integer prodCnt;
    private String category;
    private String subCategory;
    private Date date;
    private Boolean status;
    private Long userId;
}
