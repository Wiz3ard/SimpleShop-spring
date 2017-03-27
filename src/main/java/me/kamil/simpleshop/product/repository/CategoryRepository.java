package me.kamil.simpleshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kamil.simpleshop.product.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
