package miniprojectjo.domain;

// import miniprojectjo.domain.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface AuthorRepository
    extends PagingAndSortingRepository<Author, Long> {
        Optional<Author> findByEmail(String email);
        List<Author> findByIsApprove(Boolean isApprove);
        List<Author> findByAuthorNameContaining(String keyword);

    }
