package com.example.testConfig.controller;

import com.example.testConfig.entity.Category;
import com.example.testConfig.entity.Product;
import com.example.testConfig.request.CategoryRequest;
import com.example.testConfig.request.ProductRequest;
import com.example.testConfig.service.CategoryService;
import com.example.testConfig.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        List<Category> categories = categoryService.getAllCategory();
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get all success.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(categories)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        Optional<Category> category = categoryService.findCategoryById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.saveCategory(categoryRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Created success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.updateCategory(id,categoryRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("updated success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Delete success")
                .status(HttpStatus.OK)
                .code(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
