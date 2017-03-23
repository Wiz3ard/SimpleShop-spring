package me.kamil.simpleshop.product.service;

import java.util.List;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;
import org.springframework.data.domain.Sort;

public interface ProductService {

    Product findById(Long id);

    Product findByName(String name);

    void addProduct(Product product, Category category);

    void addProduct(Product product);

    List<Product> getProductsByCategoryAsc(Category category);

    List<Product> getAllProducts();

    List<Product> getAllProducts(Sort sort);
}
