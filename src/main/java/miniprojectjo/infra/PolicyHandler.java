package miniprojectjo.infra;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import miniprojectjo.config.kafka.KafkaProcessor;
import miniprojectjo.domain.AuthorRegistered;
import miniprojectjo.domain.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PolicyHandler {

    private final AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // 기본 catch-all listener
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        System.out.println("이벤트 수신 (문자열): " + eventString);
    }

    // AuthorRegistered 이벤트 전용 핸들러
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AuthorRegistered'"
    )
    public void handleAuthorRegistered(@Payload String json) {
        try {
            AuthorRegistered event = objectMapper.readValue(json, AuthorRegistered.class);
            System.out.println("✔ AuthorRegistered 이벤트 수신: " + event);

            // TODO: 여기서 집필관리 쪽 로직과 연결 (예: 원고 초기화 같은 동작)
            // 예시: writingRepository.initForAuthor(event.getId());

        } catch (Exception e) {
            System.err.println("❌ AuthorRegistered JSON 파싱 실패: " + e.getMessage());
        }
    }

    // 참고: 다른 이벤트도 동일하게 등록 가능
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AuthorRejectedExternally'"
    )
    public void handleExternalRejection(@Payload String json) {
        System.out.println("외부 작가 거절 이벤트 수신: " + json);
    }
}
