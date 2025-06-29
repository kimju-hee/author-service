package miniprojectjo.domain;

// import java.time.LocalDate;
// import java.util.*;
import lombok.*;
// import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@NoArgsConstructor
public class AuthorApproved extends AbstractEvent {

    private Long id;
    private Boolean isApprove;

    public AuthorApproved(Author aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.isApprove = aggregate.getIsApprove();
    }
}
//>>> DDD / Domain Event
