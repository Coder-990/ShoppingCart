package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(value = "cartItem")
public class CartItem {

    @Id
    private String id;
    private String offerId;
    private String action; // "ADD", "MODIFY", "DELETE"
    @DBRef
    private List<Price> prices;
    @DBRef
    private Customer customer;
}
