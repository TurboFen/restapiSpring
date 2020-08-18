package com.example.demo.controller;

import java.util.List;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.repository.jpa_data_repository_layer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> all(){
      return customerService.getAll();
    }

    @PostMapping
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerService.newCustomer(newCustomer);
    }

    @GetMapping("/{id}")
    public Customer one(@PathVariable Long id){
        return customerService.one(id);
    }

    //
    @GetMapping("/{id}/id")
    public Long getCustomerId(@PathVariable Long id ) { return customerService.getCustomerId(id);}
    //

    @PutMapping("/{id}")
    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerService.replaceCustomer(newCustomer, id);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
