package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeerservice.config.JmsConfig;
import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
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

    @Scheduled(fixedRate = 10000) // every 10 sec
    public void checkForLowInventory() {
        Iterable<Beer> beerList = beerRepository.findAll();

        beerList.forEach(beer -> {
            // how many specific beers presented in the inventory
            Integer currentInventoryOnHand = inventoryService.getOnHandInventory(beer.getId());
            log.debug("Beer: " + beer.getBeerName());
            log.debug("Min onHand is: " + beer.getMinOnHand());
            log.debug("Current inventory on hands is: " + currentInventoryOnHand);
            if (beer.getMinOnHand() >= currentInventoryOnHand) {
                // send to mq
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, mapper.toDto(beer));
            }
        });
    }
}
