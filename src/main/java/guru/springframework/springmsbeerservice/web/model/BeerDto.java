package guru.springframework.springmsbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author kas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {

    private UUID id;
    private Integer version;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private String name;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;

}
