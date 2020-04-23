package guru.springframework.springmsbeerservice.web.controller;

import guru.springframework.springmsbeerservice.Services.BeerService;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author kas
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID id) {
        BeerDto dto = beerService.getById(id);
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
