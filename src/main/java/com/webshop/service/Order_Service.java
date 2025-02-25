package com.webshop.service;
import com.webshop.request_dto.Cart_Item_DTO;
import com.webshop.request_dto.Customer_DTO;
import com.webshop.request_dto.Order_DTO;
import com.webshop.model.Cart_Item;
import com.webshop.model.Customer;
import com.webshop.model.Order;
import com.webshop.model.Product;
import com.webshop.repository.Customer_Repository;
import com.webshop.repository.Order_Repository;
import com.webshop.repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Order_Service {
    @Autowired
    private Order_Repository orderRepository;

    @Autowired
    private Product_Repository productRepository;

    @Autowired
    private Email_Sending_Service emailSendingService;

    @Autowired
    private Customer_Repository customerRepository;

    public List<Order_DTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        for (Cart_Item item : order.getItems()) {
            item.setOrder(order);
        }
        Customer customer = customerRepository.findByEmail(order.getCustomer().getEmail());
        Order newOrder;
        if (customer != null) {
            order.setCustomer(null);
            newOrder = orderRepository.save(order);
            newOrder.setCustomer(customer);
            newOrder = orderRepository.save(newOrder);
        }
        else {
            newOrder = orderRepository.save(order);
        }
        emailSendingService.sendOrderPlacement(newOrder.getCustomer().getEmail());
        return newOrder;
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not Found"));
        order.setStatus(orderDetails.getStatus());
        for (Cart_Item item : order.getItems()) {
            item.setOrder(order);
        }
        if ("Shipped".equalsIgnoreCase(orderDetails.getStatus())) {
            emailSendingService.sendShippingNotification(order.getCustomer().getEmail());
        }
        else if ("Confirmed".equalsIgnoreCase(orderDetails.getStatus())) {
            emailSendingService.sendOrderConfirmation(orderDetails.getStatus());
        }
        Order savedOrder = orderRepository.save(order);
        for (Cart_Item cartItem: savedOrder.getItems()) {
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }
        return savedOrder;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order_DTO> getOrdersByCustomerEmail(String email) {
        List<Order> orders = orderRepository.getOrdersByCustomerEmail(email);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Order_DTO convertToDTO(Order order) {
        Order_DTO orderDTO = new Order_DTO();
        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setItems(order.getItems().stream().map(item -> {
            Cart_Item_DTO cartItemDTO = new Cart_Item_DTO();
            cartItemDTO.setId(item.getId());
            cartItemDTO.setName(item.getProduct().getName());
            cartItemDTO.setPrice(item.getProduct().getPrice());
            cartItemDTO.setQuantity(item.getQuantity());
            return cartItemDTO;
        }).collect(Collectors.toList()));
        Customer_DTO customerDTO = new Customer_DTO();
        customerDTO.setId(order.getCustomer().getId());
        customerDTO.setFirstName(order.getCustomer().getFirstName());
        customerDTO.setLastName(order.getCustomer().getLastName());
        customerDTO.setEmail(order.getCustomer().getEmail());
        customerDTO.setPhone(order.getCustomer().getPhone());
        customerDTO.setAddress1(order.getCustomer().getAddress1());
        customerDTO.setAddress2(order.getCustomer().getAddress2());
        customerDTO.setCity(order.getCustomer().getCity());
        customerDTO.setPostalCode(order.getCustomer().getPostalCode());
        orderDTO.setCustomer(customerDTO);
        return orderDTO;
    }
}