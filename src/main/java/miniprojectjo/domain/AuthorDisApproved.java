package miniprojectjo.domain;

// import java.time.LocalDate;
// import java.util.*;
import lombok.*;
// import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AuthorDisApproved extends AbstractEvent {

    private Boolean isApprove;
    
    public AuthorDisApproved(Author aggregate) {
        super(aggregate);
        this.isApprove = aggregate.getIsApprove(); // 상태 전달
    }
}
//>>> DDD / Domain Event
