package project.domain;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
//<<< DDD / Aggregate Root
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Long resId;
    private Long managerId;
    private Long userId;

}
//>>> DDD / Aggregate Root
