package me.kamil.simpleshop.domain;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {

    private List<CartProduct> productList;

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    public List<CartProduct> getProductList() {
        return productList;
    }

}
