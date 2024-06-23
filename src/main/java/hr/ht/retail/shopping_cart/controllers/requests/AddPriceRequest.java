package hr.ht.retail.shopping_cart.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Builder
public record AddPriceRequest(
        @NotNull @Pattern(regexp = "RECURRING|ONE_TIME", message = "Type must be either 'RECURRING' or 'ONE_TIME'")
        String type,
        @NotNull @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal value,
        @Min(value = 7, message = "Recurrences must be 7 days or between 12 to 24 months")
        @Max(value = 24, message = "Recurrences must be 7 days or between 12 to 24 months")
        Integer recurrences
) {
}
