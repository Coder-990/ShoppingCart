package hr.ht.retail.shopping_cart.repositories;

import hr.ht.retail.shopping_cart.repositories.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
