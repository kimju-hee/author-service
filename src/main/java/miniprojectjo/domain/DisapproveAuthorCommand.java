package miniprojectjo.domain;

// import java.time.LocalDate;
// import java.util.*;
import lombok.Data;

@Data
public class DisapproveAuthorCommand {
    private String reason;// 예: "작성 이력 부족", "포트폴리오 미비" 등
}
