package me.kamil.simpleshop.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;
import me.kamil.simpleshop.product.repository.CategoryRepository;

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
        if (category == null)
            return;

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

}
