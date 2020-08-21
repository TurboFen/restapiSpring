package com.example.demo.controllerTests;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.controller.CustomerController;
import com.example.demo.entity.Customer;
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
public class ControllerTest {

    @Autowired
    CustomerController customerController;

    @MockBean
    CustomerService customerService;

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
        Mockito.when(customerService.getAll()).thenReturn(expectedCustomers);
        List<Customer> actualCustomers = customerController.all();
        Assertions.assertEquals(expectedCustomers,actualCustomers);
    }

    @Test
    void testNewCustomer(){
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(1L);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Mockito.when(customerService.newCustomer(expectedCustomer)).thenReturn(expectedCustomer);
        Customer actualCustomer = customerController.newCustomer(expectedCustomer);
        Assertions.assertEquals(expectedCustomer,actualCustomer);
    }
    @Test
    void testOne(){
        Long id = 1l;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstname("First");
        expectedCustomer.setLastname("Last1");
        Mockito.when(customerService.one(id)).thenReturn(expectedCustomer);
        Customer actualCustomer = customerController.one(id);
        Assertions.assertEquals(expectedCustomer,actualCustomer);
    }

}
