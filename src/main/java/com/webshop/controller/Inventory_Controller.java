package com.webshop.controller;
import com.webshop.model.Product;
import com.webshop.service.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class Inventory_Controller {
    @Autowired
    private Product_Service productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestBody int stock) {
        return ResponseEntity.ok(productService.updateStock(id, stock));
    }
}