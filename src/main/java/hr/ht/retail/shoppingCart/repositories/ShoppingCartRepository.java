package hr.ht.retail.shoppingCart.repositories;

import hr.ht.retail.shoppingCart.repositories.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
