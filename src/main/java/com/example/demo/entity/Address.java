package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;

    @Embedded
    private Coordinates coordinates;
}
