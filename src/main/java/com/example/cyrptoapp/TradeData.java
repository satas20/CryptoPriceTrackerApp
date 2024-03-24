package com.example.cyrptoapp;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class TradeData {

    @Id
    @GeneratedValue
    private Long id;
    private String symbol;
    private BigDecimal price;
    private BigDecimal quantity;
    private long timestamp;



    // ... other fields as needed
}