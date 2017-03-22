package me.kamil.simpleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import me.kamil.simpleshop.product.domain.Category;
import me.kamil.simpleshop.product.service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/{id}")
    public String category(Model model, @PathVariable(value = "id") int id) {

        // Check if category with id exist
        if (categoryService.findById(new Long(id)) == null) {
            return "home";
        }

        // Find category by id
        Category category = categoryService.findById(new Long(id));

        // Add model with all categories
        model.addAttribute("categories", categoryService.getAllCategories());
        // Add model with all products in category (Sorted by name)
        model.addAttribute("products", categoryService.getAllProducts(category));

        return "category";

    }
}
