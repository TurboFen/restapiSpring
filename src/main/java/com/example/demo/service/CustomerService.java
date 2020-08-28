package com.example.demo.service;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerBill;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.jpa_data_repository_layer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class CustomerService {
    private final jpa_data_repository_layer repository;
    private final BillRepository billRepository;

    public CustomerService(jpa_data_repository_layer repository,BillRepository billRepository){
        this.billRepository = billRepository;
        this.repository = repository;
    }


    public List<Customer> getAll(){
        return repository.findAll();
    }


    public Customer newCustomer( Customer newCustomer)
    {
        return repository.save(newCustomer);
    }

    public Customer saveCustomerBill( CustomerBill customerBillDto,Long id)
    {
        Customer customer = one(id);
        CustomerBill customerBill = new CustomerBill();
        customerBill.setCbNumber(customerBillDto.getCbNumber());
        customerBill.setBalance(customerBillDto.getBalance());
        customerBill.setCustomer(customer);
        customer.setCustomerBills(new ArrayList<>(Collections.singletonList(customerBill)));
        return repository.save(customer);
    }

    public Customer one(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    //
    public void DeleteCustomerBill(Long id){
    billRepository.deleteById(id);
    }

    public CustomerBill getCustomerBill(Long id){
        return billRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public CustomerBill replaceBill(CustomerBill customerBill,Long id){
        return billRepository.findById(id)
                .map(bill -> {
                    bill.setCbNumber(customerBill.getCbNumber());
                    bill.setBalance(customerBill.getBalance());
                    bill.setCustomer(customerBill.getCustomer());
                    return billRepository.save(bill);
                })
                .orElseGet(() ->{
                    customerBill.setBillId(id);
                    return billRepository.save(customerBill);
                });
    }
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
