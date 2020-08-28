package com.example.demo.repository;



import com.example.demo.entity.CustomerBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<CustomerBill, Long> {
}
