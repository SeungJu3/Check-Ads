package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import project.ConsultingApplication;
import project.domain.ConsultingCanceled;
import project.domain.ConsultingChanged;
import project.domain.ConsultingCreated;

@Entity
@Table(name = "Consulting_table")
@Data
//<<< DDD / Aggregate Root
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Long resId;

    private Long managerId;

    private Long feedbackId;

    private Long reportId;

    @PostPersist
    public void onPostPersist() {
        ConsultingCreated consultingCreated = new ConsultingCreated(this);
        consultingCreated.publishAfterCommit();

        ConsultingChanged consultingChanged = new ConsultingChanged(this);
        consultingChanged.publishAfterCommit();

        ConsultingCanceled consultingCanceled = new ConsultingCanceled(this);
        consultingCanceled.publishAfterCommit();
    }

    public static ConsultingRepository repository() {
        ConsultingRepository consultingRepository = ConsultingApplication.applicationContext.getBean(
            ConsultingRepository.class
        );
        return consultingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void cancel(ReservationCanceled reservationCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        Consulting consulting = new Consulting();
        repository().save(consulting);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationCanceled.get???()).ifPresent(consulting->{
            
            consulting // do something
            repository().save(consulting);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void change(ReservationModified reservationModified) {
        //implement business logic here:

        /** Example 1:  new item 
        Consulting consulting = new Consulting();
        repository().save(consulting);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationModified.get???()).ifPresent(consulting->{
            
            consulting // do something
            repository().save(consulting);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void assign(ReservationCreated reservationCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Consulting consulting = new Consulting();
        repository().save(consulting);

        */

        /** Example 2:  finding and process
        
        repository().findById(reservationCreated.get???()).ifPresent(consulting->{
            
            consulting // do something
            repository().save(consulting);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
