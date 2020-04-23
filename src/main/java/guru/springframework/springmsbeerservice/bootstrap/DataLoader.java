package guru.springframework.springmsbeerservice.bootstrap;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author kas
 */
@Component
public class DataLoader implements CommandLineRunner {

    public static final String BEER_UPC_1 = "018200533082";
    public static final String BEER_UPC_2 = "18200007712";
    public static final String BEER_UPC_3 = "018200113529";

    private final BeerRepository beerRepository;

    public DataLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerEntity();
    }

    private void loadBeerEntity() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Budwaiser")
                    .beerStyle("Staut")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_1)
                    .price(new BigDecimal("9.00"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_2)
                    .beerStyle("PALE_ALE")
                    .price(new BigDecimal("12.30"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("No Hammers on the bar")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_3)
                    .beerStyle("PALE_ALE")
                    .price(new BigDecimal("10"))
                    .build());
        }
    }
}
