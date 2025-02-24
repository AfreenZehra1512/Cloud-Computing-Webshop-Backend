package com.webshop.repository;
import com.webshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_Repository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}