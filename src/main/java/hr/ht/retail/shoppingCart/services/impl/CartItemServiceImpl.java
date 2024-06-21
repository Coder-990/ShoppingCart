package hr.ht.retail.shoppingCart.services.impl;

import hr.ht.retail.shoppingCart.exceptions.NotFoundException;
import hr.ht.retail.shoppingCart.repositories.CartItemRepository;
import hr.ht.retail.shoppingCart.repositories.models.CartItem;
import hr.ht.retail.shoppingCart.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

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
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(String id, CartItem cartItem){
        var existingCartItem = getCartItemById(id);
        existingCartItem.setOfferId(cartItem.getOfferId());
        existingCartItem.setAction(cartItem.getAction());
        existingCartItem.setPrices(cartItem.getPrices());
        existingCartItem.setCustomer(cartItem.getCustomer());
        return cartItemRepository.save(existingCartItem);
    }

    @Override
    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }
}
