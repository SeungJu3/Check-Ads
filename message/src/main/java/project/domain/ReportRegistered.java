package project.domain;

import java.util.*;
import lombok.*;
import project.domain.*;
import project.infra.AbstractEvent;

@Data
@ToString
public class ReportRegistered extends AbstractEvent {

    private Long id;
    private Long constId;
    private String content;
    private Long managerId;
}
