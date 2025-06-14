package com.example.demo.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private String department;
    private String name;
    private String title;
    private AddressDTO address;
}