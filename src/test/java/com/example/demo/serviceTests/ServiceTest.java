package com.example.demo.serviceTests;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.jpa_data_repository_layer;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServiceTest{
    @Autowired
    CustomerService customerService;

    @MockBean
    jpa_data_repository_layer repository;

    @Test
    void testGetAll(){
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("First");
        customer1.setLastname("Last1");

        Customer customer2 = new Customer();
        customer2.setId(10L);
        customer2.setFirstname("First2");
        customer2.setLastname("Last2");
        List<Customer> expectedCustomers = List.of(customer1,customer2);
        Mockito.when(repository.findAll()).thenReturn(expectedCustomers);
        List<Customer> actualCustomers = customerService.getAll();
        Assertions.assertEquals(expectedCustomers,actualCustomers);
    }

    @Test
    void testNewCustomer(){
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(1L);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Mockito.when(repository.save(expectedCustomer)).thenReturn(expectedCustomer);
        Customer actualCustomer = customerService.newCustomer(expectedCustomer);
        Assertions.assertEquals(expectedCustomer,actualCustomer);
    }

    @Test
    void testOne(){
        Long id = 1l;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(expectedCustomer));
        Customer actualCustomer = customerService.one(id);
        Assertions.assertEquals(expectedCustomer,actualCustomer);
    }

    @Test
    void testOne_WhenCustomerNotFound(){
        Long id = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, ()-> customerService.one(id));
    }


    @Test
    void testReplace_Customer(){
        Long id = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Customer expectedCustomer1 = new Customer();
        expectedCustomer1.setId(id);
        expectedCustomer1.setFirstname("First2");
        expectedCustomer1.setLastname("Last2");
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(expectedCustomer));
        Mockito.when(repository.save(expectedCustomer)).thenReturn(expectedCustomer1);
        Customer actualCustomer = customerService.replaceCustomer(expectedCustomer1,id);
        Assertions.assertEquals(actualCustomer.getLastname(),expectedCustomer1.getLastname());
    }

    @Test
    void testDelete(){
        Long id = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        customerService.deleteCustomer(id);
        Mockito.verify(repository,Mockito.times(1)).deleteById(id);
    }
}
