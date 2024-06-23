package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "customer")
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @DBRef
    private ShoppingCart shoppingCart;
}
