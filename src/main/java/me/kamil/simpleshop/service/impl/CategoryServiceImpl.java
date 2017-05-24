package me.kamil.simpleshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import me.kamil.simpleshop.service.CategoryService;
import me.kamil.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.domain.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(Category category) {
        return productService.getProductsByCategoryAsc(category);
    }

    @Override
    public List<Category> getAllActiveCategories() {
        List<Category> results = getAllCategories().stream()
                .filter(category -> category.isActive())
                .collect(Collectors.toList());

        return results;
    }

    @Override
    public void setActive(Category category) {
        category.setActive(!category.isActive());
        categoryRepository.save(category);
    }
}
