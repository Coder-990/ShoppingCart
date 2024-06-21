package hr.ht.retail.shoppingCart.services;

import hr.ht.retail.shoppingCart.repositories.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(String id, Customer customer);

    void deleteCustomer(String id);
}
