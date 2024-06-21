package hr.ht.retail.shoppingCart.services.mappers;

import hr.ht.retail.shoppingCart.controllers.requests.AddCustomerRequest;
import hr.ht.retail.shoppingCart.controllers.responses.CustomerResponse;
import hr.ht.retail.shoppingCart.repositories.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(target = "id", ignore = true)
    Customer toAddCustomer(AddCustomerRequest addCustomerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

    List<CustomerResponse> toListCustomerResponse(List<Customer> customers);
}
