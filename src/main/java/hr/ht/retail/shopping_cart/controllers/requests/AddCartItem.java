package hr.ht.retail.shopping_cart.controllers.requests;

import hr.ht.retail.shopping_cart.repositories.models.Customer;
import hr.ht.retail.shopping_cart.repositories.models.Price;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Builder
public record AddCartItem(
        @NotNull String offerId,
        @NotNull @Pattern(regexp = "ADD|MODIFY|DELETE", message = "Action must be either 'ADD' or 'MODIFY' or 'DELETE'")
        String action,
        @NotNull List<Price> prices,
        @NotNull Customer customer
        ) {
}
