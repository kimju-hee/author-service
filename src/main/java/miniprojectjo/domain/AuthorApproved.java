package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorApproved extends AbstractEvent {

    private Boolean isApprove;

    public AuthorApproved(Author aggregate) {
        super(aggregate);
    }

    public AuthorApproved() {
        super();
    }
}
//>>> DDD / Domain Event
