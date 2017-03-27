package me.kamil.simpleshop.product.service;

import me.kamil.simpleshop.product.domain.CartProduct;
import me.kamil.simpleshop.product.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by Kamil on 2017-03-26.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addToCart(CartProduct cartProduct) {

        ShoppingCart shoppingCart = this.getShoppingCart();

        cartProduct.setId(shoppingCart.getProductList().size() + 1);
        shoppingCart.getProductList().add(cartProduct);


    }

    @Override
    public void removeFromCart(CartProduct cartProduct) {
        ShoppingCart shoppingCart = this.getShoppingCart();
        shoppingCart.getProductList().remove(cartProduct);
    }

    @Override
    public ShoppingCart getShoppingCart() {

        ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("shoppingCart");
        if (cart == null)
            cart = createShoppingCart();
        return cart;

    }

    public ShoppingCart createShoppingCart() {

        ShoppingCart shoppingCart = new ShoppingCart();
        httpSession.setAttribute("shoppingCart", shoppingCart);
        return shoppingCart;
    }
}


