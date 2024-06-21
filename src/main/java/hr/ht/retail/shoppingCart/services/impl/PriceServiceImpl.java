package hr.ht.retail.shoppingCart.services.impl;

import hr.ht.retail.shoppingCart.exceptions.NotFoundException;
import hr.ht.retail.shoppingCart.repositories.PriceRepository;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import hr.ht.retail.shoppingCart.services.PriceService;
import jakarta.validation.Valid;
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
    public Price savePrice(@Valid Price price) {
        validateRecurrences(price);
        return priceRepository.save(price);
    }

    @Override
    public Price updatePrice(String id, Price price) {
        var existingPrice = getPriceById(id);
        validateRecurrences(price);
        existingPrice.setType(price.getType());
        existingPrice.setValue(price.getValue());
        existingPrice.setRecurrences(price.getRecurrences());
        return priceRepository.save(existingPrice);
    }

    private void validateRecurrences(Price price) {
        if ("ONE_TIME".equals(price.getType()) && price.getRecurrences() != null) {
            throw new IllegalArgumentException("Recurrences should be null for ONE_TIME type");
        } else if ("RECURRING".equals(price.getType())) {
            var recurrences = price.getRecurrences();
            if (recurrences == null || (recurrences != 7 && (recurrences < 12 || recurrences > 24))) {
                throw new IllegalArgumentException("Recurrences must be exactly 7 days or between 12 to 24 months for RECURRING type");
            }
        }
    }

    @Override
    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }

}
