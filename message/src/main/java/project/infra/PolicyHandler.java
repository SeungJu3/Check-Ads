package project.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import project.config.kafka.KafkaProcessor;
import project.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultingCreated'"
    )
    public void wheneverConsultingCreated_SendConfirmedMsg(
        @Payload ConsultingCreated consultingCreated
    ) {
        ConsultingCreated event = consultingCreated;
        System.out.println(
            "\n\n##### listener SendConfirmedMsg : " +
            consultingCreated +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultingChanged'"
    )
    public void wheneverConsultingChanged_SendConfirmedMsg(
        @Payload ConsultingChanged consultingChanged
    ) {
        ConsultingChanged event = consultingChanged;
        System.out.println(
            "\n\n##### listener SendConfirmedMsg : " +
            consultingChanged +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FeedbackRegistered'"
    )
    public void wheneverFeedbackRegistered_SendConfirmedMsg(
        @Payload FeedbackRegistered feedbackRegistered
    ) {
        FeedbackRegistered event = feedbackRegistered;
        System.out.println(
            "\n\n##### listener SendConfirmedMsg : " +
            feedbackRegistered +
            "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReportRegistered'"
    )
    public void wheneverReportRegistered_SendConfirmedMsg(
        @Payload ReportRegistered reportRegistered
    ) {
        ReportRegistered event = reportRegistered;
        System.out.println(
            "\n\n##### listener SendConfirmedMsg : " + reportRegistered + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultingCanceled'"
    )
    public void wheneverConsultingCanceled_SendCanceledMsg(
        @Payload ConsultingCanceled consultingCanceled
    ) {
        ConsultingCanceled event = consultingCanceled;
        System.out.println(
            "\n\n##### listener SendCanceledMsg : " +
            consultingCanceled +
            "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
