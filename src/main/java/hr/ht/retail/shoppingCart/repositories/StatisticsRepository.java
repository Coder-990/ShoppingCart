package hr.ht.retail.shoppingCart.repositories;

import hr.ht.retail.shoppingCart.repositories.models.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<Statistics, String> {
}
