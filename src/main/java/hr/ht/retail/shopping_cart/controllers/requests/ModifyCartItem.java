package hr.ht.retail.shopping_cart.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Builder
public record ModifyCartItem(
        @NotNull String offerId,
        @NotNull @Pattern(regexp = "ADD|MODIFY|DELETE", message = "Action must be either 'ADD' or 'MODIFY' or 'DELETE'")
        String action,
        @NotNull Price price,
        @NotNull Customer customer
) {

    @Builder
    public record Price(
            @NotNull
            @Pattern(regexp = "RECURRING|ONE_TIME", message = "Type must be either 'RECURRING' or 'ONE_TIME'")
            String type,
            @NotNull
            @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
            BigDecimal value,
            @Min(value = 7, message = "Recurrences must be 7 days or between 12 to 24 months")
            @Max(value = 24, message = "Recurrences must be 7 days or between 12 to 24 months")
            Integer recurrences
    ) {
    }

    @Builder
    public record Customer(
            @NotNull @Size(min = 3, max = 25) String firstName,
            @NotNull @Size(min = 3, max = 25) String lastName,
            @NotNull @Email String email
    ) {
    }
}
