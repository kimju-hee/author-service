package miniprojectjo.infra;

// import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import miniprojectjo.config.kafka.KafkaProcessor;
import miniprojectjo.domain.AuthorRepository;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PolicyHandler {

    private final AuthorRepository authorRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        // 기본 로그 기록
        System.out.println("이벤트 수신 (문자열): " + eventString);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AuthorRejectedExternally'"
    )
    public void handleExternalRejection(@Payload String json) {
        System.out.println("외부 작가 거절 이벤트 수신: " + json);
    }
}
