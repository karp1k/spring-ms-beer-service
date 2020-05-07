package guru.springframework.springmsbeerservice.events;

import guru.springframework.springmsbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author kas
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
