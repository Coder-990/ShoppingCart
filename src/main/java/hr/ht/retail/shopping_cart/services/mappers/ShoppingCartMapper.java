package hr.ht.retail.shopping_cart.services.mappers;

import hr.ht.retail.shopping_cart.controllers.requests.AddShoppingCart;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyShoppingCart;
import hr.ht.retail.shopping_cart.controllers.responses.ShoppingCartResponse;
import hr.ht.retail.shopping_cart.repositories.models.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShoppingCartMapper {

    @Mapping(target = "id", ignore = true)
    ShoppingCart toAddShoppingCart(AddShoppingCart addShoppingCart);

    @Mapping(target = "id", ignore = true)
    ShoppingCart toModifyShoppingCart(ModifyShoppingCart modifyShoppingCart);

    ShoppingCartResponse toShoppingCartResponse(ShoppingCart shoppingCart);

    List<ShoppingCartResponse> toListShoppingCartResponses(List<ShoppingCart> shoppingCarts);
}
