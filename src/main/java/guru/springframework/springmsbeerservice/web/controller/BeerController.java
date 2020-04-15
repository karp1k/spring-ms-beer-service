package guru.springframework.springmsbeerservice.web.controller;

import guru.springframework.springmsbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author kas
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{id}")
    public ResponseEntity getBeerById(@PathVariable UUID id) {
        return new ResponseEntity(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody BeerDto dto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeer(@PathVariable UUID id, @RequestBody BeerDto dto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
