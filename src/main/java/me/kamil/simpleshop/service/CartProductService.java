package me.kamil.simpleshop.service;

import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;

import java.util.List;

/**
 * Created by Kamil on 2017-04-16.
 */
public interface CartProductService {
    void addCartProduct(CartProduct cartProduct);

    void addCartProductList(List<CartProduct> cartProductList);

    void deleteAllCartProduct(Product product);

}
