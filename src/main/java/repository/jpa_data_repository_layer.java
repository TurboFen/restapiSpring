package repository;

import entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpa_data_repository_layer extends JpaRepository<Customer,Long> {}
