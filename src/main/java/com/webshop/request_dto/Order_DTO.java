package com.webshop.request_dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order_DTO {
    private Long id;
    private String date;
    private String status;
    private List<Cart_Item_DTO> items;
    private Customer_DTO customer;
}