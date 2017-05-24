package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.auth.service.UserService;
import me.kamil.simpleshop.domain.ShoppingCart;
import me.kamil.simpleshop.service.ProductService;
import me.kamil.simpleshop.service.ShoppingCartService;
import me.kamil.simpleshop.service.impl.OrderValidator;
import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;



    @RequestMapping(value = "/product/{pid}")
    public String productDetails(@PathVariable("pid") Integer pid, Model model) {
        Product p = productService.findById(pid.longValue());

        if (p == null) return "redirect:/home";

        model.addAttribute("product", p);

        return "product_detail";
    }





}
