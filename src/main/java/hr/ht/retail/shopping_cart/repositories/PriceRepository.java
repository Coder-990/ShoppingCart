package hr.ht.retail.shopping_cart.repositories;

import hr.ht.retail.shopping_cart.repositories.models.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
}
