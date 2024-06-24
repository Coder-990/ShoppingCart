package hr.ht.retail.shopping_cart.controllers;

import hr.ht.retail.shopping_cart.controllers.requests.AddCartItem;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyCartItem;
import hr.ht.retail.shopping_cart.controllers.responses.CartItemResponse;
import hr.ht.retail.shopping_cart.services.CartItemService;
import hr.ht.retail.shopping_cart.services.mappers.CartItemMapper;
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
@RequestMapping("/v1/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getAllCartItems() {
        log.info("Fetching all cart items ...");
        var cartItems = cartItemService.getAllCartItems();
        var listCartItemResponses = cartItemMapper.toListCartItemResponses(cartItems);
        log.info("Fetched cart items with content {}...", listCartItemResponses);
        return new ResponseEntity<>(listCartItemResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable String id) {
        log.info("Fetching cart item by id {} ...", id);
        var cartItem = cartItemService.getCartItemById(id);
        var cartItemResponse = cartItemMapper.toCartItemResponse(cartItem);
        log.info("Fetched cart item for id: {} with body {}  ...", id, cartItemResponse);
        return new ResponseEntity<>(cartItemResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartItemResponse> createCartItem(@Validated @RequestBody AddCartItem addCartItem) {
        log.info("Creating cart item with body {}...", addCartItem);
        var cartItem = cartItemMapper.toAddCartItem(addCartItem);
        var savedCartItem = cartItemService.saveCartItem(cartItem);
        var cartItemResponse = cartItemMapper.toCartItemResponse(savedCartItem);
        log.info("Created cart item for id: {} with body {}  ...", savedCartItem.getId(), cartItemResponse);
        return new ResponseEntity<>(cartItemResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable String id, @Validated @RequestBody
    ModifyCartItem modifyCartItem) {
        log.info("Modifying cart item for id: {} with body {}...", id, modifyCartItem);
        var cartItem = cartItemMapper.toModifyCartItem(modifyCartItem);
        var updatedCartItem = cartItemService.updateCartItem(id, cartItem);
        var cartItemResponse = cartItemMapper.toCartItemResponse(updatedCartItem);
        log.info("Modified cart item for id: {} with body {}...", id, cartItemResponse);
        return new ResponseEntity<>(cartItemResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String id) {
        log.info("Removing cart item with id: {}...", id);
        cartItemService.deleteCartItem(id);
        log.info("Removed cart item for id: {}...", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
