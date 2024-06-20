package hr.ht.retail.shoppingCart.controllers.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Builder
public record AddPriceRequest(
        @NotNull @Size(min = 8, max = 9) String type, // "RECURRING" or "ONE_TIME"
        @NotNull @DecimalMin("0.0") BigDecimal value,
        @NotNull @Min(0) Integer recurrences
) {
}
