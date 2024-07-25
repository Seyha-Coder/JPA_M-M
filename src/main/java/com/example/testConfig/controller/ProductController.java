package com.example.testConfig.controller;

import com.example.testConfig.entity.Product;
import com.example.testConfig.request.ProductRequest;
import com.example.testConfig.service.ProductService;
import com.example.testConfig.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        List<Product> products = productService.getAllProduct();
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get all success.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(products)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        Optional<Product> product = productService.getProductById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(product)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.saveProduct(productRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Created success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(product)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id ,@RequestBody ProductRequest productRequest){
        Product product = productService.updateProduct(id,productRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Updated success.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(product)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get success")
                .status(HttpStatus.OK)
                .code(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
