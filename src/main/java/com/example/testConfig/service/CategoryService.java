package com.example.testConfig.service;

import com.example.testConfig.entity.Category;
import com.example.testConfig.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Optional<Category> findCategoryById(Integer id);
    public Category saveCategory(CategoryRequest categoryRequest);
    public Category updateCategory(Integer id, CategoryRequest categoryRequest);
    public void deleteCategory(Integer id);
}
