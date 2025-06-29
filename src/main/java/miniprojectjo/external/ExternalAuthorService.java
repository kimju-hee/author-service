package miniprojectjo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
// import reactor.core.publisher.Mono;

@Service
public class ExternalAuthorService {

    private final WebClient webClient;

    public ExternalAuthorService(@Value("${external.author.url}") String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    public boolean author(AuthorQuery query) {
        try {
            webClient.post()
                .uri("/notify-disapproval")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // 동기식

            return true;

        } catch (Exception e) {
            System.err.println("외부 연동 실패: " + e.getMessage());
            return false;
        }
    }
}
