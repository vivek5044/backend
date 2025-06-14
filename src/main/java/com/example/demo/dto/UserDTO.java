package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private double height;
    private double weight;
    private String eyeColor;
    private HairDTO hair;
    private String ip;
    private AddressDTO address;
    private String macAddress;
    private String university;
    private BankDTO bank;
    private CompanyDTO company;
    private String ein;
    private String ssn;
    private String userAgent;
    private CryptoDTO crypto;
    private String role;
}
