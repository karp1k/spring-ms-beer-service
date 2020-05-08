package guru.springframework.springmsbeerservice.web.controller;

import guru.springframework.springmsbeerservice.services.BeerInventoryService;
import guru.springframework.springmsbeerservice.services.BeerService;
import guru.springframework.springmsbeercommon.web.model.BeerDto;
import guru.springframework.springmsbeercommon.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author kas
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private final BeerService beerService;

    // excplicitly set application/json format
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BeerDto>> getListOfBeers(
            @RequestParam(name = "page_size", required = false) Integer pageSize,
            @RequestParam(name = "page_number", required = false) Integer pageNumber,
            @RequestParam(name = "beer_name", required = false) String beerName,
            @RequestParam(name = "beer_style", required = false) BeerStyleEnum beerStyleEnum,
            @RequestParam(
                    name = "show_inventory_on_hand",
                    required = false,
                    defaultValue = "false") Boolean showInventoryOnHand) {
        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        List<BeerDto> dtoList = beerService
                .getListOfBeers(beerName, beerStyleEnum, PageRequest.of(pageNumber, pageSize), showInventoryOnHand).getContent();
        return ResponseEntity.ok(dtoList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID id,
                                               @RequestParam(
                                                       name = "show_inventory_on_hand",
                                                       required = false,
                                                       defaultValue = "false")
                                                       Boolean showInventoryOnHand) {
        BeerDto dto = beerService.getById(id, showInventoryOnHand);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/upc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc) {
        BeerDto dto = beerService.getByUpc(upc);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveBeer(@Validated @RequestBody BeerDto dto) {
        BeerDto saved = beerService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeer(@PathVariable UUID id, @Validated @RequestBody BeerDto dto) {
        beerService.update(id, dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
