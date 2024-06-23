package hr.ht.retail.shopping_cart.repositories;

import hr.ht.retail.shopping_cart.repositories.models.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
}
