package miniprojectjo.domain;

import miniprojectjo.external.AuthorQuery;
import miniprojectjo.external.ExternalAuthorService;
// import miniprojectjo.domain.AuthorRegistered;
import miniprojectjo.AuthorApplication;
import javax.persistence.*;

import lombok.Data;

import java.util.List;
// import lombok.Data;
import java.util.Date;
// import java.time.LocalDate;
// import java.util.Map;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import java.util.Collections;


@Entity
@Table(name="Author_table")
@Data
//<<< DDD / Aggregate Root
public class Author  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    private Long id;    

    @Column(nullable = false)    
    private String email;    
        
    private String authorName;    
        
    private String introduction;    
        
    private String featuredWorks;    
        
    @ElementCollection
    private List<Portfolio> portfolios;    
    
    @Column(nullable = false)
    private Boolean isApprove;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = new Date();
    }

    @PostPersist
    public void onPostPersist(){


        AuthorRegistered authorRegistered = new AuthorRegistered(this);
        authorRegistered.publishAfterCommit();

    
    }

    public static AuthorRepository repository(){
        AuthorRepository authorRepository = AuthorApplication.applicationContext.getBean(AuthorRepository.class);
        return authorRepository;
    }



    //<<< Clean Arch / Port Method
        public void approveAuthor(ApproveAuthorCommand cmd){
        this.isApprove = true;
        repository().save(this);

        AuthorApproved authorApproved = new AuthorApproved(this);
        authorApproved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void disapproveAuthor(DisapproveAuthorCommand cmd){
        this.isApprove = false;
        repository().save(this);

        AuthorQuery authorQuery = new AuthorQuery();
        authorQuery.setAuthorId(this.id);
        authorQuery.setReason("요건 미달로 비승인");

        ExternalAuthorService externalService =
            AuthorApplication.applicationContext.getBean(ExternalAuthorService.class);
        externalService.author(authorQuery);

        AuthorDisApproved event = new AuthorDisApproved(this);
        event.publishAfterCommit();
    }
//>>> Clean Arch / Port Method



}
//>>> DDD / Aggregate Root
