package hr.ht.retail.shoppingCart.repositories;

import hr.ht.retail.shoppingCart.repositories.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
