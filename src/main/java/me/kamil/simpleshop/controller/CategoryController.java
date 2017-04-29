package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/{id}")
    public String category(Model model, @PathVariable(value = "id") Integer id) {

        Category category = categoryService.findById(id.longValue());

        if (category == null)
            return "home";

        model.addAttribute("products", categoryService.getAllProducts(category));

        return "category";

    }
}
