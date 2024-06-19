package hr.ht.retail.shoppingCart.controllers;

import hr.ht.retail.shoppingCart.repositories.models.Price;
import hr.ht.retail.shoppingCart.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        var createdPrice = priceService.createPrice(price);
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable String id) {
        var price = priceService.getPriceById(id);
        return price.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Price>> getAllPrices() {
        var prices = priceService.getAllPrices();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable String id, @RequestBody Price price) {
        try {
            var updatedPrice = priceService.updatePrice(id, price);
            return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable String id) {
        priceService.deletePrice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
