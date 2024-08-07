package com.example.testConfig.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private Double price;
    private int qty;
    private String description;
    private List<Integer> categoryIds;
}
