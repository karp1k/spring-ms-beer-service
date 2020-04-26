package guru.springframework.springmsbeerservice.repositories;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kas
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Optional<Beer> findByUpc(String upc);
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
}
