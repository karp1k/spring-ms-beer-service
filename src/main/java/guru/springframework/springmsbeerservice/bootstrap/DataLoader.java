package guru.springframework.springmsbeerservice.bootstrap;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author kas
 */
//@Component instead bootstrap data with data.sql
public class DataLoader implements CommandLineRunner {

    public static final String BEER_UPC_1 = "018200533082";
    public static final String BEER_UPC_2 = "18200007712";
    public static final String BEER_UPC_3 = "018200113529";
    public static final UUID BEER_ID_1 = UUID.fromString("fba04b35-ad91-4c95-8fea-63202d82f69a");
    public static final UUID BEER_ID_2 = UUID.fromString("a4e9bc4b-75cb-4d1f-b36d-604d60d472db");
    public static final UUID BEER_ID_3 = UUID.fromString("84c8a160-ccd6-4779-9346-3fb94c77ebd1");

    private final BeerRepository beerRepository;

    public DataLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       // loadBeerEntity();
    }

    private void loadBeerEntity() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .id(BEER_ID_1)
                    .beerName("Budwaiser")
                    .beerStyle("STAUT")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_1)
                    .price(new BigDecimal("9.00"))
                    .build());
            beerRepository.save(Beer.builder()
                    .id(BEER_ID_2)
                    .beerName("Galaxy Cat")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_2)
                    .beerStyle("PALE_ALE")
                    .price(new BigDecimal("12.30"))
                    .build());
            beerRepository.save(Beer.builder()
                    .id(BEER_ID_3)
                    .beerName("No Hammers on the bar")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_UPC_3)
                    .beerStyle("PORTER")
                    .price(new BigDecimal("10"))
                    .build());
        }
    }
}
