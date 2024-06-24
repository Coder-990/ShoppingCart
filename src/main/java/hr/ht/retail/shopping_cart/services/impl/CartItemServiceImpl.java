package hr.ht.retail.shopping_cart.services.impl;

import hr.ht.retail.shopping_cart.exceptions.NotFoundException;
import hr.ht.retail.shopping_cart.repositories.CartItemRepository;
import hr.ht.retail.shopping_cart.repositories.models.CartItem;
import hr.ht.retail.shopping_cart.services.CartItemService;
import hr.ht.retail.shopping_cart.services.CustomerService;
import hr.ht.retail.shopping_cart.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CustomerService customerService;
    private final PriceService priceService;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(String id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("Could not find cart item by this id %s").formatted(id)));
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        cartItem.setPrice(priceService.savePrice(cartItem.getPrice()));
        cartItem.setCustomer(customerService.saveCustomer(cartItem.getCustomer()));
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(String id, CartItem cartItem) {
        var existingCartItem = getCartItemById(id);
        existingCartItem.setId(existingCartItem.getId());
        existingCartItem.setOfferId(cartItem.getOfferId());
        existingCartItem.setAction(cartItem.getAction());
        existingCartItem.setPrice(existingCartItem.getPrice());
        existingCartItem.setCustomer(existingCartItem.getCustomer());
        return cartItemRepository.save(existingCartItem);
    }

    @Override
    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }
}
