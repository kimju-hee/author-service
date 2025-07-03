package miniprojectjo.domain;

import java.util.*;
import lombok.*;
import miniprojectjo.infra.AbstractEvent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 예기치 않은 필드 무시
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

    @Override
    public String getEventType() {
        return "AuthorRegistered";
    }
}
