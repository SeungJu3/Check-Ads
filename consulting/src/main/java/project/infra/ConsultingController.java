package project.infra;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.domain.*;

//<<< Clean Arch / Inbound Adaptor

@CrossOrigin(origins = "*") // 또는 특정 도메인으로 설정
@RestController
@RequestMapping(value="/api/consultings")
@Transactional
public class ConsultingController {

    @Autowired
    ConsultingRepository consultingRepository;

    @PostMapping
    public ResponseEntity<Consulting> createConsulting(@RequestBody Consulting dto) {
        try { 
            Consulting consulting = new Consulting();
            consulting.setDate(dto.getDate());
            consulting.setResId(dto.getId());
            consulting.setUserId(dto.getUserId());
            consultingRepository.save(consulting);

            Consulting savedConsulting = consultingRepository.save(consulting);
            return ResponseEntity.ok(savedConsulting);
        } catch (Exception e) {
            e.printStackTrace(); // 로그에 에러 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Consulting> getConsultingsByUserId(@PathVariable Long userId) {
        return consultingRepository.findByUserId(userId);
    }
}
//>>> Clean Arch / Inbound Adaptor

