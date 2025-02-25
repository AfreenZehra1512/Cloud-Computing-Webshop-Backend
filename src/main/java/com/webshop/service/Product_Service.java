package com.webshop.service;
import com.webshop.model.Product;
import com.webshop.repository.Product_Repository;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class Product_Service {

    @Autowired
    private Product_Repository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product createProduct(String name, String description, String category, double price, int stock, MultipartFile image) throws IOException {
        String imageUrl = saveImage(image);
        Product product = new Product(null, name, description, category, price, stock, imageUrl);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String description, String category, double price, int stock, MultipartFile image) throws IOException, ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found"));
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setStock(stock);
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            product.setImage(imageUrl);
        }
        return productRepository.save(product);
    }

    private String saveImage(MultipartFile image) throws IOException {
        return "";
    }

    public Product updateStock(Long id, int stock) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
        product.setStock(stock);
        return productRepository.save(product);
    }
}