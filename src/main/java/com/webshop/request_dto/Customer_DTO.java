package com.webshop.request_dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer_DTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
}