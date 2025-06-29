package miniprojectjo.external;

import org.springframework.stereotype.Service;

@Service
public class ExternalAuthorService {

    public boolean author(AuthorQuery query) {
        // 실제 외부 API 호출 로직이 들어갈 수 있음
        System.out.println("외부 시스템 호출: " + query);
        return true; // 성공 응답을 가정
    }
}
