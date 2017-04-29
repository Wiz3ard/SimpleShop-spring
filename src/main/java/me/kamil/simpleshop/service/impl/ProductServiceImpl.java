package me.kamil.simpleshop.service.impl;

import java.util.List;

import me.kamil.simpleshop.service.CartProductService;
import me.kamil.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.domain.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductService cartProductService;

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

    @Override
    public List<Product> getProductsByCategoryAsc(Category category) {
        return productRepository.findByCategoryOrderByNameAsc(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Product> getAllProducts(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        cartProductService.deleteAllCartProduct(product);
        productRepository.delete(product);
    }
}
