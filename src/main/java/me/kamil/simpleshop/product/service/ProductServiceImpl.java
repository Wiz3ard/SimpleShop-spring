package me.kamil.simpleshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.domain.Product;
import me.kamil.simpleshop.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void addProduct(Product product, Category category) {

        if (product == null || category == null) {
            return;
        }

        product.setCategory(category);
        productRepository.save(product);

    }

    @Override
    public void addProduct(Product product) {
        if (product == null || product.getCategory() == null) {
            return;
        }

        productRepository.save(product);
    }

    // Get product from category sorted by name ascending
    @Override
    public List<Product> getProductsByCategoryAsc(Category category) {
        return productRepository.findByCategoryOrderByNameAsc(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
