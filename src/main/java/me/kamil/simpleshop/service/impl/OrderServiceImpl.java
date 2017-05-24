package me.kamil.simpleshop.service.impl;

import me.kamil.simpleshop.auth.domain.User;
import me.kamil.simpleshop.auth.service.UserService;
import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.domain.repository.OrderRepository;
import me.kamil.simpleshop.service.CartProductService;
import me.kamil.simpleshop.service.OrderService;
import me.kamil.simpleshop.service.ProductService;
import me.kamil.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kamil on 2017-04-15.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartProductService cartProductService;
    @Autowired
    private ProductService productService;

    @Override
    public void addOrder(Order order) {

        order.setStatus("STATUS_PENDING");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        order.setUser(user);

        List<CartProduct> cartProductList = shoppingCartService.getShoppingCart().getProductList();
        cartProductService.addCartProductList(cartProductList);
        order.setProductList(cartProductList);

        orderRepository.save(order);

        cartProductList.forEach(i -> {
            int amount = i.getAmount();
            Product p = i.getProduct();
            productService.decreseProductAmount(p, amount);
        });


    }

    @Override
    public void editOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void changeStatus(Order order, String status) {
        order.setStatus(status);
        orderRepository.save(order);
    }


    @Override
    public List<Order> getOrderByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> getOrderByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}