package guru.springframework.springmsbeerservice.Services;

import guru.springframework.springmsbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author kas
 */
public interface BeerService {

    BeerDto getById(UUID id);
    BeerDto save(BeerDto dto);
    void update(UUID id, BeerDto dto);




}
