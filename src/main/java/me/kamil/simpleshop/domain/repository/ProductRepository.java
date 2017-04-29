package me.kamil.simpleshop.domain.repository;

import java.util.List;

import me.kamil.simpleshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import me.kamil.simpleshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findByCategoryOrderByNameAsc(Category category);
}
