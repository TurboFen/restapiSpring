package com.example.demo.service;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerBill;
import com.example.demo.repository.jpa_data_repository_layer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Customer saveCustomerBill( CustomerBill customerBill ,Long id)
    {
        Customer customer = one(id);
        List<CustomerBill> list = new ArrayList<>();
        CustomerBill one = new CustomerBill();
        one.setBalance(customerBill.getBalance());
        one.setCbNumber(customerBill.getCbNumber());
        one.setCustomer(customer);
        list.add(one);
        customer.setCustomerBills(list);
        return repository.save(customer);
        
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
