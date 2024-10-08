package project.domain;

import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

@Data
@ToString
public class ConsultingChanged extends AbstractEvent {

    private Long id;
    private Date date;
    private Long resId;
    private Long managerId;
    private Long feedbackId;
    private Long reportId;
}
