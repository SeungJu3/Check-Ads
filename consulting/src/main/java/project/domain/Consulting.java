package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.context.event.EventListener;

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
    // private Long feedbackId;
    // private Long reportId;
    private Long userId;

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

    public static void cancel(ReservationCanceled reservationCanceled) {
        // 예약 취소에 따른 Consulting 객체 찾기
        repository().findById(reservationCanceled.getId()).ifPresent(consulting -> {
            // Consulting 객체를 삭제
            repository().delete(consulting);
        });
    }

    public static void change(ReservationModified reservationModified) {
        
        // 예약 변경에 따른 Consulting 객체 찾기
        repository().findById(reservationModified.getId()).ifPresent(consulting -> {
            // 필요한 필드를 수정
            consulting.setDate(reservationModified.getDate()); // 새 날짜로 업데이트 (여기서 새로운 날짜를 가져와야 함)
            
            // 변경된 Consulting 객체 저장
            repository().save(consulting);
        });
    }

    // @EventListener
    // public static void assign(ReservationCreated reservationCreated) {
        
    //     // 새로운 Consulting 객체 생성
    //     Consulting consulting = new Consulting();
    //     consulting.setDate(reservationCreated.getDate()); // 현재 날짜로 설정 (필요에 따라 조정)
    //     consulting.setResId(reservationCreated.getId());
    //     consulting.setUserId(reservationCreated.getUserId());
    //     consulting.setManagerId(1L); // 1번 컨설턴트로 배정
        
    //     // Consulting 저장
    //     repository().save(consulting);
    // }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
