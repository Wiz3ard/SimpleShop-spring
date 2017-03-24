package me.kamil.simpleshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import me.kamil.simpleshop.product.service.CategoryService;


@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {

        return "home";
    }
}
