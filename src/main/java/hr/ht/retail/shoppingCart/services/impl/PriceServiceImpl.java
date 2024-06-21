package hr.ht.retail.shoppingCart.services.impl;

import hr.ht.retail.shoppingCart.exceptions.NotFoundException;
import hr.ht.retail.shoppingCart.repositories.PriceRepository;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import hr.ht.retail.shoppingCart.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPriceById(String id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("Could not find price by this id %s").formatted(id)));
    }

    @Override
    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Price updatePrice(String id, Price price) {
        var existingPrice = getPriceById(id);
        existingPrice.setType(price.getType());
        existingPrice.setValue(price.getValue());
        existingPrice.setRecurrences(price.getRecurrences());
        return priceRepository.save(existingPrice);
    }

    @Override
    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }
}
