package guru.springframework.springmsbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import guru.springframework.springmsbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

// instead of MockMvcRequestBuilders using RestDocumentationRequestBuilders for Documentation porpoise
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeerDto;
    String beerDtoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        validBeerDto = BeerDto.builder()
                .name("Duff")
                .upc(1L)
                .price(new BigDecimal("21.0"))
                .beerStyle(BeerStyleEnum.ALE).build();
        beerDtoJson = objectMapper.writeValueAsString(validBeerDto);
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(
                get("/api/v1/beer/{id}",
                        UUID.randomUUID().toString())
                        //.param("iscold", "yes") // not real query param, just for document demonstration
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andDo(
                document("v1/beer-get",
                        pathParameters(parameterWithName("id").description("UUID of desired beer to get")),
                        //pathParameters(parameterWithName("iscold").description("Is cold Query parameter")),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version of Dto"),
                                fieldWithPath("createdDate").description("Date Created"),
                                fieldWithPath("lastModifiedDate").description("Date Updated"),
                                fieldWithPath("name").description("Name of Beer"),
                                fieldWithPath("beerStyle").description("Beer style"),
                                fieldWithPath("upc").description("Description"),
                                fieldWithPath("price").description("Beer price"),
                                fieldWithPath("quantityOnHand").description("Quantity on hand"))
                        ));
    }

    @Test
    void saveBeer() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
        mockMvc.perform(
                post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated())
        .andDo(document("v1/beer-new",
                requestFields(
                        fields.withPath("id").ignored(),
                        fields.withPath("version").ignored(),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastModifiedDate").ignored(),
                        fields.withPath("name").description("Name of Beer"),
                        fields.withPath("beerStyle").description("Beer style"),
                        fields.withPath("upc").description("Description").attributes(),
                        fields.withPath("price").description("Beer price"),
                        fields.withPath("quantityOnHand").ignored()
                )));
    }

    @Test
    void updateBeer() throws Exception {
        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }


    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> clazz) {
            this.constraintDescriptions = new ConstraintDescriptions(clazz);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints")
                    .value(StringUtils
                            .collectionToDelimitedString(
                                    this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}