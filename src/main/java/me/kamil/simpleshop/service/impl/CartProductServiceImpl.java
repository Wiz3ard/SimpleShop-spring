package me.kamil.simpleshop.service.impl;

import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.domain.repository.CartProductRepository;
import me.kamil.simpleshop.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kamil on 2017-04-16.
 */
@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public void addCartProduct(CartProduct cartProduct) {
        cartProductRepository.save(cartProduct);
    }

    @Override
    public void addCartProductList(List<CartProduct> cartProductList) {
        if (cartProductList == null) return;
        cartProductList.forEach(i -> {
            addCartProduct(i);
        });

    }

    @Override
    public void deleteAllCartProduct(Product product) {
        if (cartProductRepository.findByProduct(product) == null) return;
        cartProductRepository.deleteByProduct(product);
    }


}
