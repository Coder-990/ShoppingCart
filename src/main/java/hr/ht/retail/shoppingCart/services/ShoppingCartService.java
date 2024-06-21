package hr.ht.retail.shoppingCart.services;

import hr.ht.retail.shoppingCart.repositories.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAllShoppingCarts();

    ShoppingCart getShoppingCartById(String id);

    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart updateShoppingCart(String id, ShoppingCart shoppingCart);

    void deleteShoppingCart(String id);
}
