package com.example.testConfig.service;


import com.example.testConfig.entity.Product;
import com.example.testConfig.request.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService  {
    List<Product> getAllProduct();
    Optional<Product> getProductById(Integer id);
    Product saveProduct(ProductRequest productRequest);
    Product updateProduct(Integer id, ProductRequest productRequest);
    void deleteProduct(Integer id);
}
