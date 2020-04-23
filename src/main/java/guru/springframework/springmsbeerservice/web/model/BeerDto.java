package guru.springframework.springmsbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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

    @Null
    private UUID id; // read-only properties for client
    @Null
    private Integer version; // read-only properties for client
    @Null
    private OffsetDateTime createdDate; // read-only properties for client
    @Null
    private OffsetDateTime lastModifiedDate; // read-only properties for client
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotNull
    private BeerStyleEnum beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    private Integer quantityOnHand;

}
