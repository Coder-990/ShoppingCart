package hr.ht.retail.shoppingCart.repositories;

import hr.ht.retail.shoppingCart.repositories.models.CartItem;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCartItem extends MongoRepository<CartItem, String> {
}
