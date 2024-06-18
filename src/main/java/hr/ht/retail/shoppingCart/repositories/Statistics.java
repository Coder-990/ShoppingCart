package hr.ht.retail.shoppingCart.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Statistics extends MongoRepository<Statistics, String> {
}
