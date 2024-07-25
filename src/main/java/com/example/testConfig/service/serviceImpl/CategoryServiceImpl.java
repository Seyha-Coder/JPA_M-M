package com.example.testConfig.service.serviceImpl;

import com.example.testConfig.entity.Category;
import com.example.testConfig.entity.Product;
import com.example.testConfig.exception.CustomNotfoundException;
import com.example.testConfig.repository.CategoryRepository;
import com.example.testConfig.request.CategoryRequest;
import com.example.testConfig.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id not found.")
        );
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Integer id, CategoryRequest categoryRequest) {
        Optional<Category> findById = findCategoryById(id);
        if(findById == null){
            throw new CustomNotfoundException("Category does not exist");
        }
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id not found")
        );
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Category id not found.")
        );
        categoryRepository.deleteById(id);
    }
}
