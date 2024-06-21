package hr.ht.retail.shoppingCart.controllers;

import hr.ht.retail.shoppingCart.controllers.requests.AddPriceRequest;
import hr.ht.retail.shoppingCart.controllers.requests.ModifyPriceRequest;
import hr.ht.retail.shoppingCart.controllers.responses.PriceResponse;
import hr.ht.retail.shoppingCart.services.PriceService;
import hr.ht.retail.shoppingCart.services.mappers.PriceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getAllPrices() {
        log.info("Fetching all prices ...");
        var prices = priceService.getAllPrices();
        var listPriceResponse = priceMapper.toListPriceResponse(prices);
        log.info("Fetched prices with content {}...", listPriceResponse);
        return new ResponseEntity<>(listPriceResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceResponse> getPriceById(@PathVariable String id) {
        log.info("Fetching price by id {} ...", id);
        var price = priceService.getPriceById(id);
        var priceResponse = priceMapper.toPriceResponse(price);
        log.info("Fetched price for id: {}, with body {}...", id, priceResponse);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> createPrice(@Validated @RequestBody AddPriceRequest addPriceRequest) {
        log.info("Creating price with body {}...", addPriceRequest);
        var price = priceMapper.toAddPrice(addPriceRequest);
        var createdPrice = priceService.savePrice(price);
        var priceResponse = priceMapper.toPriceResponse(createdPrice);
        log.info("Price for id: {} created {}...", createdPrice.getId(), priceResponse);
        return new ResponseEntity<>(priceResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> updatePrice(@PathVariable String id, @Validated @RequestBody ModifyPriceRequest modifyPriceRequest) {
        log.info("Modifying price with body {}...", modifyPriceRequest);
        var price = priceMapper.toModifyPrice(modifyPriceRequest);
        var modyfiedPrice = priceService.updatePrice(id, price);
        var priceResponse = priceMapper.toPriceResponse(modyfiedPrice);
        log.info("Price for id: {} modified {}...", modyfiedPrice.getId(), priceResponse);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable String id) {
        log.info("Removing price with id: {}...", id);
        priceService.deletePrice(id);
        log.info("Removed price for id: {} and subscription is canceled", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
