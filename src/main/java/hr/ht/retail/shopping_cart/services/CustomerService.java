package hr.ht.retail.shopping_cart.services;

import hr.ht.retail.shopping_cart.repositories.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(String id, Customer customer);

    void deleteCustomer(String id);
}
