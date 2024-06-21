package hr.ht.retail.shoppingCart.services;

import hr.ht.retail.shoppingCart.repositories.models.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItems();

    CartItem getCartItemById(String id);

    CartItem saveCartItem(CartItem cartItem);

    CartItem updateCartItem(String id, CartItem cartItem);

    void deleteCartItem(String id);
}
