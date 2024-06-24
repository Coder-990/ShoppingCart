package hr.ht.retail.shopping_cart.controllers.requests;

import hr.ht.retail.shopping_cart.repositories.models.CartItem;
import lombok.Builder;

import java.util.List;

@Builder
public record ModifyShoppingCart(List<CartItem> cartItems) {
}
