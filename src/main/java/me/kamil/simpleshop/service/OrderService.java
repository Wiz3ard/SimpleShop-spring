package me.kamil.simpleshop.service;

import me.kamil.simpleshop.auth.domain.User;
import me.kamil.simpleshop.domain.Order;

import java.util.List;

/**
 * Created by Kamil on 2017-04-15.
 */
public interface OrderService {

    void addOrder(Order order);

    void editOrder(Order order);

    void deleteOrder(Order order);

    void changeStatus(Order order, String status);

    List<Order> getOrderByUser(User user);

    Order getOrderById(Long id);

    List<Order> getOrderByStatus(String status);

    List<Order> getAllOrders();
}
