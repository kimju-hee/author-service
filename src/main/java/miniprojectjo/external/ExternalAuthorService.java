package miniprojectjo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalAuthorService {

    private final WebClient webClient;

    public ExternalAuthorService(@Value("${api.url.author}") String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl("http://" + baseUrl) // ex: http://localhost:8082
            .build();
    }

    public void author(AuthorQuery query) {
        webClient.post()
            .uri("/external/author/disapprove") // 호출할 엔드포인트
            .bodyValue(query)
            .retrieve()
            .bodyToMono(Void.class)
            .block(); // 동기 호출
    }
}
