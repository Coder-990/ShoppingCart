package hr.ht.retail.shopping_cart.services.mappers;

import hr.ht.retail.shopping_cart.controllers.requests.AddCartItem;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyCartItem;
import hr.ht.retail.shopping_cart.controllers.responses.CartItemResponse;
import hr.ht.retail.shopping_cart.repositories.models.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {

    @Mapping(target = "price.type", source = "type")
    @Mapping(target = "price.value", source = "value")
    @Mapping(target = "price.recurrences", source = "recurrences")
    @Mapping(target = "customer.firstName", source = "firstName")
    @Mapping(target = "customer.lastName", source = "lastName")
    @Mapping(target = "customer.email", source = "email")
    @Mapping(target = "id",ignore = true)
    CartItem toAddCartItem(AddCartItem addCartItem);

    @Mapping(target = "price.type", source = "type")
    @Mapping(target = "price.value", source = "value")
    @Mapping(target = "price.recurrences", source = "recurrences")
    @Mapping(target = "customer.firstName", source = "firstName")
    @Mapping(target = "customer.lastName", source = "lastName")
    @Mapping(target = "customer.email", source = "email")
    @Mapping(target = "id",ignore = true)
    CartItem toModifyCartItem(ModifyCartItem modifyCartItem);

    CartItemResponse toCartItemResponse(CartItem cartItem);

    List<CartItemResponse> toListCartItemResponses(List<CartItem> cartItems);


}
