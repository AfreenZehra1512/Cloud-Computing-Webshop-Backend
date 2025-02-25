package com.webshop.repository;
import com.webshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Order_Repository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByCustomerEmail(String email);
}