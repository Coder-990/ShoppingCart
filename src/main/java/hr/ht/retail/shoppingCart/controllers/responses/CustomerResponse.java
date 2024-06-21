package hr.ht.retail.shoppingCart.controllers.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

@Builder
public record CustomerResponse(
        @NotNull @Size (min = 3, max = 25) String firstName,
        @NotNull @Size (min = 3, max = 25) String lastName,
        @NotNull @Email String email
) {
}
