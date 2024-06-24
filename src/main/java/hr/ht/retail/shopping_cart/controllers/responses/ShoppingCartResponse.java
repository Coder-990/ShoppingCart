package hr.ht.retail.shopping_cart.controllers.responses;

import hr.ht.retail.shopping_cart.repositories.models.CartItem;

import java.util.List;

public record ShoppingCartResponse(List<CartItem> cartItems) {
}
