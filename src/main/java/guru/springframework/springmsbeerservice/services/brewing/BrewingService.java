package guru.springframework.springmsbeerservice.services.brewing;

import guru.springframework.springmsbeerservice.config.JmsConfig;
import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import guru.springframework.springmsbeerservice.services.BeerInventoryService;
import guru.springframework.springmsbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author kas
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService inventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper mapper;

    @Scheduled(fixedRate = 15000) // every 10 sec
    public void checkForLowInventory() {
        Iterable<Beer> beerList = beerRepository.findAll();

        beerList.forEach(beer -> {
            // how many specific beers presented in the inventory
            Integer currentInventoryOnHand = inventoryService.getOnHandInventory(beer.getId());

            if (beer.getMinOnHand() >= currentInventoryOnHand) {
                log.debug("Send request for beer to inventory...");
                log.debug("Beer: " + beer.getBeerName());
                log.debug("Min onHand is: " + beer.getMinOnHand());
                log.debug("Current inventory on hands is: " + currentInventoryOnHand);
                log.debug("======================================");
                // send to mq
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, mapper.toDto(beer));
            }
        });
    }
}
