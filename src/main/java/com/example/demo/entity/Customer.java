package com.example.demo.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstname;
    @Column(name = "last_name",nullable = false)
    private String lastname;



    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerBill> customerBills;
    

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CustomerBill> getCustomerBills() {
        return customerBills;
    }

    public void setCustomerBills(List<CustomerBill> customerBills) {
        this.customerBills = customerBills;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
