package com.webshop.repository;
import com.webshop.model.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_Item_Repository extends JpaRepository<Cart_Item, Long> {

}