package hr.ht.retail.shopping_cart.controllers;

import hr.ht.retail.shopping_cart.controllers.requests.AddShoppingCart;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyShoppingCart;
import hr.ht.retail.shopping_cart.controllers.responses.ShoppingCartResponse;
import hr.ht.retail.shopping_cart.services.ShoppingCartService;
import hr.ht.retail.shopping_cart.services.mappers.ShoppingCartMapper;
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
@RequestMapping("/v1/shoppingCarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponse>> getAllShoppingCarts() {
        log.info("Fetching all shopping carts ...");
        var shoppingCart = shoppingCartService.getAllShoppingCarts();
        var listShoppingCartResponses = shoppingCartMapper.toListShoppingCartResponses(shoppingCart);
        log.info("Fetched price with content {}...", listShoppingCartResponses);
        return new ResponseEntity<>(listShoppingCartResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartById(@PathVariable String id) {
        log.info("Fetching shopping cart by id {} ...", id);
        var shoppingCart = shoppingCartService.getShoppingCartById(id);
        var shoppingCartResponse = shoppingCartMapper.toShoppingCartResponse(shoppingCart);
        log.info("Fetched shopping cart for id: {}, with body {}...", id, shoppingCartResponse);
        return new ResponseEntity<>(shoppingCartResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartResponse> createShoppingCart(@Validated @RequestBody AddShoppingCart addShoppingCart) {
        log.info("Creating shopping cart with body {}...", addShoppingCart);
        var shoppingCart = shoppingCartMapper.toAddShoppingCart(addShoppingCart);
        var createdShoppingCart = shoppingCartService.saveShoppingCart(shoppingCart);
        var shoppingCartResponse = shoppingCartMapper.toShoppingCartResponse(createdShoppingCart);
        log.info("Created shopping cart for id: {} with body {}...", createdShoppingCart.getId(), shoppingCartResponse);
        return new ResponseEntity<>(shoppingCartResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartResponse> updateShoppingCart(@PathVariable String id, @Validated @RequestBody ModifyShoppingCart modifyShoppingCart) {
        log.info("Modifying shopping cart with body {}...", modifyShoppingCart);
        var shoppingCart = shoppingCartMapper.toModifyShoppingCart(modifyShoppingCart);
        var updatedShoppingCart = shoppingCartService.updateShoppingCart(id, shoppingCart);
        var shoppingCartResponse = shoppingCartMapper.toShoppingCartResponse(updatedShoppingCart);
        log.info("Modified shopping cart for id: {} with body {}...", updatedShoppingCart.getId(), shoppingCartResponse);
        return new ResponseEntity<>(shoppingCartResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable String id) {
        log.info("Removing shopping cart with id: {}...", id);
        shoppingCartService.deleteShoppingCart(id);
        log.info("Removed shopping cart for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
