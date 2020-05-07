package guru.springframework.springmsbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
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
public class BeerDto implements Serializable {

    static final long serialVersionUID = -1193785131030705907L;
    // Override PropertyNamingStrategy
    // @JsonProperty("beerId") rename property from id to beerId in JSON
    @Null
    private UUID id; // read-only properties for client
    @Null
    private Integer version; // read-only properties for client

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate; // read-only properties for client

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime lastModifiedDate; // read-only properties for client

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @NotNull
    private String upc;

    // BigDecimal to String
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;

    // Just for fun with Custom serializer and deserializer
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    // From yyyy-mm-dd to yyyymmdd
    //@JsonSerialize(using = LocalDateSerializer.class)
    //private LocalDate localDate;

}
