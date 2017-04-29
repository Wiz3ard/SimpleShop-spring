package me.kamil.simpleshop.domain.repository;

import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kamil on 2017-04-15.
 */
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    //  List<CartProduct> findByOrder(Order order);
    List<CartProduct> findByProduct(Product product);

    void deleteByProduct(Product product);
}
