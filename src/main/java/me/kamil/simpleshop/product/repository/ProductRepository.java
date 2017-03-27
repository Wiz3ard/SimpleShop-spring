package me.kamil.simpleshop.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findByCategoryOrderByNameAsc(Category category);
}
