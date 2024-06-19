package hr.ht.retail.shoppingCart.services.impl;

import hr.ht.retail.shoppingCart.repositories.PriceRepository;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import hr.ht.retail.shoppingCart.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Optional<Price> getPriceById(String id) {
        return priceRepository.findById(id);
    }

    @Override
    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Price updatePrice(String id, Price price) {
        getPriceById(id)
                .map(existingPrice -> {
                    existingPrice.setType(price.getType());
                    existingPrice.setValue(price.getValue());
                    existingPrice.setRecurrences(price.getRecurrences());
                    return priceRepository.save(existingPrice);
                })
                .orElseThrow(() -> new RuntimeException("Price not found"));
        return price;
    }

    @Override
    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }
}
