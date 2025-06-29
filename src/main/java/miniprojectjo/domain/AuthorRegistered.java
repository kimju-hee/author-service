package miniprojectjo.domain;

// import java.time.LocalDate;
import java.util.*;
import lombok.*;
// import miniprojectjo.domain.*;
import miniprojectjo.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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
        this.id = aggregate.getId();
        this.email = aggregate.getEmail();
        this.authorName = aggregate.getAuthorName();
        this.introduction = aggregate.getIntroduction();
        this.featuredWorks = aggregate.getFeaturedWorks();
        this.portfolios = aggregate.getPortfolios();
        this.isApprove = aggregate.getIsApprove();
    }
}
//>>> DDD / Domain Event
