package hr.ht.retail.shoppingCart.services.mappers;

import hr.ht.retail.shoppingCart.controllers.requests.AddPriceRequest;
import hr.ht.retail.shoppingCart.controllers.requests.ModifyPriceRequest;
import hr.ht.retail.shoppingCart.controllers.responses.PriceResponse;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "type", target = "type")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "recurrences", target = "recurrences")
    Price toAddPrice(AddPriceRequest addPriceRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "type", target = "type")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "recurrences", target = "recurrences")
    Price toModifyPrice(ModifyPriceRequest priceRequest);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "recurrences", target = "recurrences")
    PriceResponse toPriceResponse(Price price);

    List<PriceResponse> toListPriceResponse(List<Price> prices);

}
