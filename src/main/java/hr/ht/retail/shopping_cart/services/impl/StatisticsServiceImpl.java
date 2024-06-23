package hr.ht.retail.shopping_cart.services.impl;

import hr.ht.retail.shopping_cart.exceptions.NotFoundException;
import hr.ht.retail.shopping_cart.repositories.StatisticsRepository;
import hr.ht.retail.shopping_cart.repositories.models.Statistics;
import hr.ht.retail.shopping_cart.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    @Override
    public Statistics getStatisticsById(String id) {
        return statisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("Could not find statistics by this id %s").formatted(id)));
    }

    @Override
    public Statistics saveStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics updateStatisticsById(String id, Statistics statistics) {
        var existingStatistics = getStatisticsById(id);
        existingStatistics.setCartItem(statistics.getCartItem());
        existingStatistics.setOfferId(statistics.getOfferId());
        existingStatistics.setAction(statistics.getAction());
        existingStatistics.setCount(statistics.getCount());
        existingStatistics.setPeriod(statistics.getPeriod());
        return statisticsRepository.save(existingStatistics);
    }

    @Override
    public void deleteStatistics(String id) {
        statisticsRepository.deleteById(id);
    }
}
