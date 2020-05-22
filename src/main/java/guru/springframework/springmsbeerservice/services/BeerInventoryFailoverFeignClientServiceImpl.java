package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeercommon.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author kas
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerInventoryFailoverFeignClientServiceImpl implements BeerInventoryFeignClientService {

    private final BeerInventoryFailoverServiceFeignClient failoverFeignClientService;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        log.debug("getOnHandInventory from failover service");
        return failoverFeignClientService.getOnHandInventory();
    }
}
