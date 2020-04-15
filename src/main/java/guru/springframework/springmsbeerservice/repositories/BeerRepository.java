package guru.springframework.springmsbeerservice.repositories;

import guru.springframework.springmsbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author kas
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
