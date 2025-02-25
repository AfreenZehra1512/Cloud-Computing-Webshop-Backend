package com.webshop.request_dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product_DTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
    private String image;
}