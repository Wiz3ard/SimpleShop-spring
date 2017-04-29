package me.kamil.simpleshop.service;

import java.util.List;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.domain.Product;
import org.springframework.data.domain.Sort;

public interface ProductService {

    Product findById(Long id);

    Product findByName(String name);

    void addProduct(Product product, Category category);

    void addProduct(Product product);

    List<Product> getProductsByCategoryAsc(Category category);

    List<Product> getAllProducts();

    List<Product> getAllProducts(Sort sort);

    void updateProduct(Product product);

    void deleteProduct(Product product);
}

