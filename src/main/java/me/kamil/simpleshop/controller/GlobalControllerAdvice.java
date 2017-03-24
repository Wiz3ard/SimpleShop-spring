package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;
import me.kamil.simpleshop.product.service.CategoryService;
import me.kamil.simpleshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Created by Kamil on 2017-03-24.
 */

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @ModelAttribute("categories")
    public List<Category> categoriesList() {
        return categoryService.getAllCategories();
    }

    @ModelAttribute("products")
    public List<Product> productList() {
        return productService.getAllProducts(new Sort(Sort.Direction.ASC, "id"));
    }
}
