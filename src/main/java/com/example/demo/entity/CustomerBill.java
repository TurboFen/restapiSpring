package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "customerBills")
@EntityListeners(AuditingEntityListener.class)
public class CustomerBill {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BillId;

    @Column(name = "cbNumber", nullable = false)
    private long cbNumber;

    @Column(name = "client_balance", nullable = false)
    private long balance = 0;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }


    public Long getBillId() {
        return BillId;
    }

    public long getCbNumber() {
        return cbNumber;
    }

    public void setBillId(Long billId) {
        BillId = billId;
    }

    public void setCbNumber(long cbNumber) {
        this.cbNumber = cbNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
