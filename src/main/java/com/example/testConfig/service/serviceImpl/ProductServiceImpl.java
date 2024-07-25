package com.example.testConfig.service.serviceImpl;

import com.example.testConfig.entity.Category;
import com.example.testConfig.entity.Product;
import com.example.testConfig.exception.CustomNotfoundException;
import com.example.testConfig.repository.CategoryRepository;
import com.example.testConfig.repository.ProductRepository;
import com.example.testConfig.request.ProductRequest;
import com.example.testConfig.service.CategoryService;
import com.example.testConfig.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        productRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Product id not found.")
        );
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Optional<Category> category = categoryService.findCategoryById(productRequest.getCategoryId());
        if(!category.isPresent()){
            throw new CustomNotfoundException("Category does not exist.");
        }
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .qty(productRequest.getQty())
                .price(productRequest.getPrice())
                .build();
        product.setCategory(category.get());
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Integer id, ProductRequest productRequest){
        Optional<Category> category = categoryService.findCategoryById(productRequest.getCategoryId());
        Product product = productRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("product is not found")
        );
        product.setName(productRequest.getName());
        product.setDescription(product.getDescription());
        product.setQty(productRequest.getQty());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category.get());
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Integer id) {
        productRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Product id not found.")
        );
        productRepository.deleteById(id);
    }
}
