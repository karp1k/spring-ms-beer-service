package guru.springframework.springmsbeerservice.events;

import guru.springframework.springmsbeerservice.web.model.BeerDto;

/**
 * @author kas
 */
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
