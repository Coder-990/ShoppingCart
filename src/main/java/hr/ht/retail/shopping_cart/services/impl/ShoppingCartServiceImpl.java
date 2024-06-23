package hr.ht.retail.shopping_cart.services.impl;

import hr.ht.retail.shopping_cart.exceptions.NotFoundException;
import hr.ht.retail.shopping_cart.repositories.ShoppingCartRepository;
import hr.ht.retail.shopping_cart.repositories.models.ShoppingCart;
import hr.ht.retail.shopping_cart.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getShoppingCartById(String id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("Could not find shopping cart by this id %s").formatted(id)));
    }

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateShoppingCart(String id, ShoppingCart shoppingCart) {
        var existingShoppingCart = getShoppingCartById(id);
        existingShoppingCart.setItems(shoppingCart.getItems());
        return shoppingCartRepository.save(existingShoppingCart);
    }

    @Override
    public void deleteShoppingCart(String id) {
        shoppingCartRepository.deleteById(id);
    }
}
