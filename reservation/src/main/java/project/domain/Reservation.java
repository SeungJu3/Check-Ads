package project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.web.client.RestTemplate;

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

        // Consulting 생성 요청
        createConsulting();
    }

    private void createConsulting() {
        RestTemplate restTemplate = new RestTemplate();
        String consultingServiceUrl = "https://8084-seungju3-checkads-bejf89dkv3a.ws-us116.gitpod.io/api/consultings"; // Consulting 서비스의 URL

        // 새로운 Consulting 객체 생성
        Consulting newConsulting = new Consulting();
        newConsulting.setResId(this.id);
        newConsulting.setUserId(1L);
        newConsulting.setDate(this.date); // 현재 날짜로 설정 (필요에 따라 조정)

        // POST 요청
        try {
            restTemplate.postForEntity(consultingServiceUrl, newConsulting, Consulting.class);
        } catch (Exception e) {
            e.printStackTrace(); // 에러 스택 트레이스 출력
            // 에러 처리 로직 추가
            System.err.println("Error creating consulting: " + e.getMessage());
        }
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }
}
//>>> DDD / Aggregate Root
