package me.kamil.simpleshop.service;

import java.util.List;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.domain.Product;

public interface CategoryService {

    Category findById(Long id);

    Category findByName(String name);

    void addCategory(Category category);

    List<Category> getAllCategories();

    List<Product> getAllProducts(Category category);

    List<Category> getAllActiveCategories();

    void setActive(Category category);

}
