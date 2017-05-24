package me.kamil.simpleshop.controller.admin;

import me.kamil.simpleshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kamil on 2017-05-24.
 */
@Controller
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "admin/orders")
    public String ordersGet(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());

        return "admin/orders";
    }
}
