package guru.springframework.springmsbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import static guru.springframework.springmsbeerservice.bootstrap.DataLoader.BEER_UPC_1;
import static org.junit.jupiter.api.Assertions.*;
// Profile for SNAKE_CASE of JSON properties names
@ActiveProfiles("snake")
@JsonTest
class BeerDtoJsonTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    public void serializeDto() throws JsonProcessingException {
        BeerDto dto = getDto();
        String jsonString = mapper.writeValueAsString(dto);
        System.out.println(jsonString);

    }

    @Test
    void deserializeString() throws JsonProcessingException {
        String jsonString = "{\"id\":\"e03a21c9-622c-414a-bb7d-084c6cc74a62\",\"version\":null,\"created_date\":\"2020-04-23T12:58:47+0300\",\"last_modified_date\":\"2020-04-23T12:58:47+0300\",\"name\":\"Name\",\"beer_style\":\"ALE\",\"upc\":1,\"price\":\"10\",\"quantity_on_hand\":null}";
        BeerDto dto = mapper.readValue(jsonString, BeerDto.class);
        System.out.println(dto);
    }

    BeerDto getDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Name")
                .beerStyle(BeerStyleEnum.ALE)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .upc(BEER_UPC_1)
                .price(BigDecimal.TEN)
                //.localDate(LocalDate.now())
                .build();
    }


}