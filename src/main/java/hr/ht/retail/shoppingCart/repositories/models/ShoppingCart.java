package hr.ht.retail.shoppingCart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(value = "shoppingCart")
public class ShoppingCart {

    @Id
    private String id;
    @DBRef
    private List<CartItem> items;
}
