package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeercommon.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author kas
 */
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class BeerInventoryServiceFeignClientImpl implements BeerInventoryService {

    private final BeerInventoryFeignClientService beerInventoryFeignClientService;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service through OpenFeign with beerId: {}", beerId);
        ResponseEntity<List<BeerInventoryDto>> inventoryDtoList = beerInventoryFeignClientService.getOnHandInventory(beerId);
        return Objects.requireNonNull(inventoryDtoList.getBody()).stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
    }
}
