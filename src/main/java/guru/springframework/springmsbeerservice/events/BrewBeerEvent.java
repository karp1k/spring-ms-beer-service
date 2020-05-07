package guru.springframework.springmsbeerservice.events;

import guru.springframework.springmsbeerservice.web.model.BeerDto;

/**
 * @author kas
 */
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
