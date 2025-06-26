package miniprojectjo.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorRegistered extends AbstractEvent {

    private Long id;
    private String email;
    private String authorName;
    private String introduction;
    private String featuredWorks;
    private List<Portfolio> portfolios;
    private Boolean isApprove;

    public AuthorRegistered(Author aggregate) {
        super(aggregate);
    }

    public AuthorRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
