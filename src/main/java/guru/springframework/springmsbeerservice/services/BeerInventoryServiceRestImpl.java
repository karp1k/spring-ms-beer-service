package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeerservice.services.ms.inventory.model.BeerInventoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * @author kas
 */
@Service
public class BeerInventoryServiceRestImpl implements BeerInventoryService {

    private static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    private final String beerInventoryServiceHost;

    public BeerInventoryServiceRestImpl(RestTemplateBuilder restTemplateBuilder,
                                        @Value("${ms.brewery.beer-inventory-service.host}") String beerInventoryServiceHost) {
        this.restTemplate = restTemplateBuilder.build();
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        //ResponseEntity<List<BeerInventoryDto>> inventoryDtoList = restTemplate.getForEntity(beerInventoryServiceHost + INVENTORY_PATH, (Class<List<BeerInventoryDto>>)(Class<?>)List.class, beerId);
        ResponseEntity<List<BeerInventoryDto>> inventoryDtoList = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, beerId);

        return inventoryDtoList.getBody().stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
    }
}
