package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/{id}")
    public String category(Model model, @PathVariable(value = "id") Integer id) {


        Optional<Category> opt = Optional.ofNullable(categoryService.findById(id.longValue()));


        if (!opt.isPresent()) {
            return "home";
        }

        Category category = categoryService.findById(new Long(id));

        model.addAttribute("products", categoryService.getAllProducts(category));

        return "category";

    }
}
