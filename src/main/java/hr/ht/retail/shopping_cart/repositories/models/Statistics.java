package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "statistics")
public class Statistics {

    @Id
    private String id;
    @DBRef
    private CartItem cartItem;
    private String offerId;
    private String action;
    private int count;
    private String period;
}
