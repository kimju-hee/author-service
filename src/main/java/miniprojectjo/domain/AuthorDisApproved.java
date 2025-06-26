package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorDisApproved extends AbstractEvent {

    private Boolean isApprove;

    public AuthorDisApproved(Author aggregate) {
        super(aggregate);
    }

    public AuthorDisApproved() {
        super();
    }
}
//>>> DDD / Domain Event
