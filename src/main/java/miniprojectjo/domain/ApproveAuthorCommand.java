package miniprojectjo.domain;

// import java.time.LocalDate;
// import java.util.*;
import lombok.Data;

@Data
public class ApproveAuthorCommand {
    private String approvedBy;      // 승인 처리자 (ex. admin email)
    private String approvalComment; // 비고, 메모
    // private Boolean isApprove;
}
