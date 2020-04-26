package guru.springframework.springmsbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmsbeerservice.services.BeerService;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import guru.springframework.springmsbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static guru.springframework.springmsbeerservice.bootstrap.DataLoader.BEER_UPC_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    BeerDto validBeerDto;
    String beerDtoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validBeerDto = BeerDto.builder()
                .beerName("Duff")
                .upc(BEER_UPC_1)
                .price(new BigDecimal("21.0"))
                .beerStyle(BeerStyleEnum.ALE).build();
        beerDtoJson = objectMapper.writeValueAsString(validBeerDto);
    }

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any())).willReturn(validBeerDto);
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        given(beerService.save(any())).willReturn(validBeerDto);
        mockMvc.perform(
                post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson)).andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        doNothing().when(beerService).update(any(), any());
        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}