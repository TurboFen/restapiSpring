package com.example.demo.service;

import java.util.List;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.jpa_data_repository_layer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service

public class CustomerService {
    private final jpa_data_repository_layer repository;

    public CustomerService(jpa_data_repository_layer repository){
        this.repository = repository;
    }


    public List<Customer> getAll(){
        return repository.findAll();
    }


    public Customer newCustomer( Customer newCustomer)
    {
        return repository.save(newCustomer);
    }


    public Customer one( Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    // нужен ли такой метод
    public Long getCustomerId(Long id){return repository.getCustomerId(id);}
    //

    public Customer replaceCustomer(Customer newCustomer,Long id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstname(newCustomer.getFirstname());
                    customer.setLastname(newCustomer.getLastname());;
                    customer.setBalance(newCustomer.getBalance());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }

    public void deleteCustomer(Long id){
        repository.deleteById(id);
    }
}
