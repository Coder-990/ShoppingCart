package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "cartItem")
public class CartItem {

    @Id
    private String id;
    private String offerId;
    private String action;
    @DBRef
    private Price price;
    @DBRef
    private Customer customer;
}
