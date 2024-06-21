package hr.ht.retail.shoppingCart.services;

import hr.ht.retail.shoppingCart.repositories.models.Statistics;

import java.util.List;

public interface StatisticsService {
    List<Statistics> getAllStatistics();

    Statistics getStatisticsById(String id);

    Statistics saveStatistics(Statistics statistics);

    Statistics updateStatisticsById(String id, Statistics statistics);

    void deleteStatistics(String id);
}
