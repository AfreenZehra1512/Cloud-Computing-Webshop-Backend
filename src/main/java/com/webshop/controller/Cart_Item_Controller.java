package com.webshop.controller;
import com.webshop.model.Cart_Item;
import com.webshop.service.Cart_Item_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class Cart_Item_Controller {
    @Autowired
    private Cart_Item_Service cartItemService;

    @GetMapping
    public List<Cart_Item> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart_Item> getCartItemById(@PathVariable Long id) {
        Optional<Cart_Item> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cart_Item> saveCartItem(@RequestBody Cart_Item cartItem) {
        Cart_Item savedCartItem = cartItemService.saveCartItem(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}