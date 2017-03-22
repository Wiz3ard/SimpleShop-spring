package me.kamil.simpleshop.product.service;

import java.util.List;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;

public interface CategoryService {

    Category findById(Long id);

    Category findByName(String name);

    void addCategory(Category category);

    List<Category> getAllCategories();

    List<Product> getAllProducts(Category category);
}
