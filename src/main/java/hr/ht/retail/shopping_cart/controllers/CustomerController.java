package hr.ht.retail.shopping_cart.controllers;

import hr.ht.retail.shopping_cart.controllers.requests.AddCustomerRequest;
import hr.ht.retail.shopping_cart.controllers.requests.ModifyCustomerRequest;
import hr.ht.retail.shopping_cart.controllers.responses.CustomerResponse;
import hr.ht.retail.shopping_cart.services.CustomerService;
import hr.ht.retail.shopping_cart.services.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        log.info("Fetching all customers ...");
        var customers = customerService.getAllCustomers();
        var listCustomerResponse = customerMapper.toListCustomerResponse(customers);
        log.info("Fetched customers with content {} ...", listCustomerResponse);
        return new ResponseEntity<>(listCustomerResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) {
        log.info("Fetching customer by id {}", id);
        var customer = customerService.getCustomerById(id);
        var customerResponse = customerMapper.toCustomerResponse(customer);
        log.info("Fetched customer by id {} with body {}...", id, customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> createCustomer(@Validated @RequestBody AddCustomerRequest addCustomerRequest) {
        log.info("Creating customer with body {}...", addCustomerRequest);
        var customer = customerMapper.toAddCustomer(addCustomerRequest);
        var savedCustomer = customerService.saveCustomer(customer);
        var customerResponse = customerMapper.toCustomerResponse(savedCustomer);
        log.info("Created customer for id: {} with body {}...", savedCustomer.getId(), customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id, @Validated @RequestBody
    ModifyCustomerRequest modifyCustomerRequest) {
        log.info("Modifying customer for id: {} with body {}...", id, modifyCustomerRequest);
        var customer = customerMapper.toModifyCustomer(modifyCustomerRequest);
        var updatedCustomer = customerService.updateCustomer(id, customer);
        var customerResponse = customerMapper.toCustomerResponse(updatedCustomer);
        log.info("Modifying customer for id: {} with body {}...", updatedCustomer.getId(), customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        log.info("Removing customer with id: {}...", id);
        customerService.deleteCustomer(id);
        log.info("Removed customer for id: {}...", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
