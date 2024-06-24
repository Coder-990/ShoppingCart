package hr.ht.retail.shopping_cart.services.impl;

import hr.ht.retail.shopping_cart.exceptions.NotFoundException;
import hr.ht.retail.shopping_cart.repositories.CartItemRepository;
import hr.ht.retail.shopping_cart.repositories.models.CartItem;
import hr.ht.retail.shopping_cart.repositories.models.Customer;
import hr.ht.retail.shopping_cart.repositories.models.Price;
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
        cartItem.setPrice(savePrice(cartItem.getPrice()));
        cartItem.setCustomer(saveCustomer(cartItem.getCustomer()));
        return cartItemRepository.save(cartItem);
    }

    private Price savePrice(Price cartItemPrice) {
        var price = Price.builder()
                .type(cartItemPrice.getType())
                .value(cartItemPrice.getValue())
                .recurrences(cartItemPrice.getRecurrences())
                .build();
        return priceService.savePrice(price);
    }

    private Customer saveCustomer(Customer cartItemCustomer) {
        var customer = Customer.builder()
                .firstName(cartItemCustomer.getFirstName())
                .lastName(cartItemCustomer.getLastName())
                .email(cartItemCustomer.getEmail())
                .build();
        return customerService.saveCustomer(customer);
    }


    @Override
    public CartItem updateCartItem(String id, CartItem cartItem) {
        var existingCartItem = getCartItemById(id);
        existingCartItem.setOfferId(cartItem.getOfferId());
        existingCartItem.setAction(cartItem.getAction());
        existingCartItem.setPrice(cartItem.getPrice());
        existingCartItem.setCustomer(cartItem.getCustomer());
        return cartItemRepository.save(existingCartItem);
    }

    @Override
    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }
}
