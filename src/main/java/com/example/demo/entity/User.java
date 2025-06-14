package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String eyeColor;
    private String ip;
    private String macAddress;
    private String university;
    private String ein;
    private String ssn;
    private String userAgent;
    private String role;

    // Nested fields — will be defined separately
    @Embedded
    private Hair hair;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "user_address")),
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "state", column = @Column(name = "user_state")),
            @AttributeOverride(name = "stateCode", column = @Column(name = "user_state_code")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "user_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "user_country")),
            @AttributeOverride(name = "coordinates.lat", column = @Column(name = "user_lat")),
            @AttributeOverride(name = "coordinates.lng", column = @Column(name = "user_lng"))
    })
    private Address address;

    @Embedded
    private Bank bank;

    @Embedded
    private Company company;

    @Embedded
    private Crypto crypto;
}


