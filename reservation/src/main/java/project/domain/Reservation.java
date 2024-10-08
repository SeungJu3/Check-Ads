package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import project.ReservationApplication;
import project.domain.ReservationCanceled;
import project.domain.ReservationCreated;
import project.domain.ReservationModified;

@Entity
@Table(name = "Reservation_table")
@Data
//<<< DDD / Aggregate Root
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String request;

    private String product;

    private Integer prodCnt;

    private String category;

    private String subCategory;

    private Date date;

    private Boolean status;

    private Long userId;

    @PostPersist
    public void onPostPersist() {
        ReservationCreated reservationCreated = new ReservationCreated(this);
        reservationCreated.publishAfterCommit();

        ReservationModified reservationModified = new ReservationModified(this);
        reservationModified.publishAfterCommit();

        ReservationCanceled reservationCanceled = new ReservationCanceled(this);
        reservationCanceled.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }
}
//>>> DDD / Aggregate Root
