package guru.springframework.springmsbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto beerDto;
    String beerDtoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        beerDto = BeerDto.builder().build();
        beerDtoJson = objectMapper.writeValueAsString(beerDto);
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        mockMvc.perform(
                post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson)).andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}