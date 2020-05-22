package guru.springframework.springmsbeerservice.services;

import java.util.UUID;

/**
 * @author kas
 */
public interface BeerInventoryService {

    String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";

    Integer getOnHandInventory(UUID beerId);
}
