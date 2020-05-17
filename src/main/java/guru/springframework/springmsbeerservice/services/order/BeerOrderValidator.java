package guru.springframework.springmsbeerservice.services.order;

import guru.springframework.springmsbeercommon.web.model.BeerOrderDto;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kas
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {
    private final BeerRepository beerRepository;

    public Boolean validate(BeerOrderDto beerOrderDto) {
        AtomicInteger beerNotFound = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {

            if (!beerRepository.findByUpc(beerOrderLineDto.getUpc()).isPresent()) {
                beerNotFound.incrementAndGet();
            }
        });
        return beerNotFound.get() == 0;

    }


}
