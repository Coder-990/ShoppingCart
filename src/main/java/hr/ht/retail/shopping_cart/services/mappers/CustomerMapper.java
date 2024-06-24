package hr.ht.retail.shopping_cart.services.mappers;

import hr.ht.retail.shopping_cart.controllers.requests.AddCustomerRequest;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyCustomerRequest;
import hr.ht.retail.shopping_cart.controllers.responses.CustomerResponse;
import hr.ht.retail.shopping_cart.repositories.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    Customer toAddCustomer(AddCustomerRequest addCustomerRequest);

    Customer toModifyCustomer(ModifyCustomerRequest modifyCustomerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

    List<CustomerResponse> toListCustomerResponse(List<Customer> customers);
}
