package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeerservice.web.model.BeerDto;
import guru.springframework.springmsbeerservice.web.model.BeerPageList;
import guru.springframework.springmsbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @author kas
 */
public interface BeerService {

    BeerDto getById(UUID id, Boolean showInventoryOnHand);
    BeerDto save(BeerDto dto);
    void update(UUID id, BeerDto dto);


    BeerPageList getListOfBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest of, Boolean showInventoryOnHand);
}
