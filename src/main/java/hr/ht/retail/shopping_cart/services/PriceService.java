package hr.ht.retail.shopping_cart.services;

import hr.ht.retail.shopping_cart.repositories.models.Price;

import java.util.List;

public interface PriceService {
    List<Price> getAllPrices();

    Price getPriceById(String id);

    Price savePrice(Price price);

    Price updatePrice(String id, Price price);

    void deletePrice(String id);
}
