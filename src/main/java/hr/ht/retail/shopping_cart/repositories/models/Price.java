package hr.ht.retail.shopping_cart.repositories.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(value = "price")
public class Price {

    @Id
    private String id;
    private String type;
    private BigDecimal value;
    private Integer recurrences;
}
