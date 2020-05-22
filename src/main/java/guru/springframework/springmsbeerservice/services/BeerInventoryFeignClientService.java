package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeercommon.web.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

/**
 * @author kas
 */
@FeignClient(name = "inventory-service") // name should be like the spring.application.name property
public interface BeerInventoryFeignClientService {
    /*
    * explicitly set PathVariable name property to beerId beacuse of a Exception:
    * PathVariable annotation was empty on param 0
    * */
    @GetMapping(value = BeerInventoryService.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable("beerId") UUID beerId);
}
