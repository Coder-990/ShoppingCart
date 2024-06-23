package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
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
    private Price price;
    @DBRef
    private Customer customer;

    @Data
    @Builder
    public static class Price {

        private String type; // "RECURRING" or "ONE_TIME"
        private BigDecimal value;
        private Integer recurrences; // only for RECURRING type
    }


    @Builder
    public static class Customer {

        private String firstName;
        private String lastName;
        private String email;
    }
}
