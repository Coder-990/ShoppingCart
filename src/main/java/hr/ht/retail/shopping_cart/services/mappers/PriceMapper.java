package hr.ht.retail.shopping_cart.services.mappers;

import hr.ht.retail.shopping_cart.controllers.requests.AddPriceRequest;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyPriceRequest;
import hr.ht.retail.shopping_cart.controllers.responses.PriceResponse;
import hr.ht.retail.shopping_cart.repositories.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    Price toAddPrice(AddPriceRequest addPriceRequest);

    Price toModifyPrice(ModifyPriceRequest priceRequest);

    PriceResponse toPriceResponse(Price price);

    List<PriceResponse> toListPriceResponse(List<Price> prices);

}
