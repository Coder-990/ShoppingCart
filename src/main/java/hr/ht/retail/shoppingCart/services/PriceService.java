package hr.ht.retail.shoppingCart.services;

import hr.ht.retail.shoppingCart.repositories.models.Price;

import java.util.List;

public interface PriceService {
    List<Price> getAllPrices();

    Price getPriceById(String id);

    Price createPrice(Price price);

    Price updatePrice(String id, Price price);

    void deletePrice(String id);
}
