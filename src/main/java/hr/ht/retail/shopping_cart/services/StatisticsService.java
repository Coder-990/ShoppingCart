package hr.ht.retail.shopping_cart.services;

import hr.ht.retail.shopping_cart.repositories.models.Statistics;

import java.util.List;

public interface StatisticsService {
    List<Statistics> getAllStatistics();

    Statistics getStatisticsById(String id);

    Statistics saveStatistics(Statistics statistics);

    Statistics updateStatisticsById(String id, Statistics statistics);

    void deleteStatistics(String id);
}
