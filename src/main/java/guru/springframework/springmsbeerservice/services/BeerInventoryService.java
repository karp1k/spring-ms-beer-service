package guru.springframework.springmsbeerservice.services;

import java.util.UUID;

/**
 * @author kas
 */
public interface BeerInventoryService {

    Integer getOnHandInventory(UUID beerId);
}
