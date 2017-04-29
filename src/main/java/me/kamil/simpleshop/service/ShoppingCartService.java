package me.kamil.simpleshop.service;

import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.ShoppingCart;

public interface ShoppingCartService {
    void addToCart(CartProduct cartProduct);

    void removeFromCart(CartProduct cartProduct);

    ShoppingCart getShoppingCart();
}
