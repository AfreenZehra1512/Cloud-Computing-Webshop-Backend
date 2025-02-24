package com.webshop.service;
import com.webshop.model.Cart_Item;
import com.webshop.repository.Cart_Item_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Cart_Item_Service {
    @Autowired
    private Cart_Item_Repository cartItemRepository;

    public List<Cart_Item> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<Cart_Item> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    public Cart_Item saveCartItem(Cart_Item cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}