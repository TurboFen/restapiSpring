package controller;

import java.util.List;

import entity.Customer;
import repository.jpa_data_repository_layer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final jpa_data_repository_layer repository;

    public CustomerController(jpa_data_repository_layer repository){
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Customer> all(){
        return repository.findAll();
    }

    @PostMapping()
    public Customer newCustomer(@RequestBody Customer newCustomer)
    {
        return repository.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    public Customer one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
