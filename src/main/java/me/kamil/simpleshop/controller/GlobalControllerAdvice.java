package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.service.CategoryService;
import me.kamil.simpleshop.service.ProductService;
import me.kamil.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @ModelAttribute("categories")
    public List<Category> categoriesList() {
        return categoryService.getAllCategories();
    }

    @ModelAttribute("activeCategories")

    public List<Category> activeCategoriesList() {
        return categoryService.getAllActiveCategories();
    }

    @ModelAttribute("products")
    public List<Product> productList() {
        return productService.getAllProducts(new Sort(Sort.Direction.ASC, "id"));
    }

    @ModelAttribute("shoppingCart")
    public List<CartProduct> shoppingCartProducts() {
        return shoppingCartService.getShoppingCart().getProductList();
    }
}
