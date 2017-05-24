package me.kamil.simpleshop.controller.admin;

import me.kamil.simpleshop.domain.Category;
import me.kamil.simpleshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kamil on 2017-05-24.
 */
@Controller
public class AdminCategoryController {


    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "admin/category", method = RequestMethod.GET)
    public String category() {
        return "admin/category";
    }

    @RequestMapping(value = "admin/category/edit/{id}", method = RequestMethod.GET)
    public String categoryEditGet(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id.longValue()));
        return "admin/category_edit";
    }

    @RequestMapping(value = "admin/category/edit/{id}")
    public String categoryEditPost(@PathVariable(value = "id") Integer id, @ModelAttribute("category") Category category, Model model, BindingResult bindingResult) {
        category.setId(id.longValue());

        if (category.getName().length() < 3 || category.getName().length() > 32) {
            bindingResult.rejectValue("name", "Category.wrongName");
            return "admin/category_edit";
        }

        categoryService.addCategory(category);
        return "admin/category";
    }

    @RequestMapping(value = "admin/category/active/{id}")
    public String categorySetActive(@PathVariable(value = "id") Integer id) {
        Category category = categoryService.findById(id.longValue());
        if (category == null) return "redirect:/admin/category";
        categoryService.setActive(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "admin/category/add")
    public String categoryAddGet(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category_add";
    }

    @RequestMapping(value = "admin/category/add", method = RequestMethod.POST)
    public String categoryAddPost(Model model, @ModelAttribute("category") Category category, BindingResult bindingResult) {

        if (category == null) {
            return "admin/category_add";
        }
        if (category.getName().length() < 3 || category.getName().length() > 32) {
            bindingResult.rejectValue("name", "Category.wrongName");
            return "admin/category_add";
        }

        categoryService.addCategory(category);

        return "redirect:/admin/category";
    }
}
