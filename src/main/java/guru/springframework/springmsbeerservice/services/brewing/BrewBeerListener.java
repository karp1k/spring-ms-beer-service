package guru.springframework.springmsbeerservice.services.brewing;

import guru.springframework.springmsbeerservice.config.JmsConfig;
import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.events.BrewBeerEvent;
import guru.springframework.springmsbeerservice.events.NewInventoryEvent;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import guru.springframework.springmsbeercommon.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author kas
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BrewBeerListener {

    private final BeerRepository repository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto dto = event.getBeerDto();
        Optional<Beer> beerOp = repository.findById(dto.getId());
        // simplified brewing process
        beerOp.ifPresent(beer -> {
            log.debug("Brewing new quantity of beers {}...", beer.getBeerName());
            dto.setQuantityOnHand(beer.getQuantityToBrew());
            log.debug("Brewed beer {} with quantity {}", beer.getBeerName(), beer.getQuantityToBrew());
            NewInventoryEvent newInventoryEvent = new NewInventoryEvent(dto);
            jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
        });

    }
}
