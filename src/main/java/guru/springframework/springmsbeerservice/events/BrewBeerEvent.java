package guru.springframework.springmsbeerservice.events;

import guru.springframework.springmsbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author kas
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
