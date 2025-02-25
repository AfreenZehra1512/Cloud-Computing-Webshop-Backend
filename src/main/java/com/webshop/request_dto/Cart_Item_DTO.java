package com.webshop.request_dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart_Item_DTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}