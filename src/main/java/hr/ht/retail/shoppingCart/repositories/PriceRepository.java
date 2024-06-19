package hr.ht.retail.shoppingCart.repositories;

import hr.ht.retail.shoppingCart.repositories.models.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
}
