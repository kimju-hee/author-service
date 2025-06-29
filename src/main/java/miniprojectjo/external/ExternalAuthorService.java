package miniprojectjo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import javax.annotation.PostConstruct;

@Service
public class ExternalAuthorService {

    @Value("${api.url.author}")
    private String baseUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    public void author(AuthorQuery query) {
        webClient.post()
            .uri("/external/author/callback") // 예시 경로
            .bodyValue(query)
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        System.out.println("외부 서비스 호출 완료: " + query);
    }
}
