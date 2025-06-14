package com.example.demo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;
    private CoordinatesDTO coordinates;
}