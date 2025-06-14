package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Crypto {
    private String coin;
    private String wallet;
    private String network;
}