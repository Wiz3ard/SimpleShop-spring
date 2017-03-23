package me.kamil.simpleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.kamil.simpleshop.product.domain.Product;
import me.kamil.simpleshop.product.service.CategoryService;
import me.kamil.simpleshop.product.service.ProductService;
import me.kamil.simpleshop.product.service.ProductValidator;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator validator;

    @RequestMapping(value = "/admin")
    public String admin() {

        return "admin/admin";
    }

    @RequestMapping(value = "admin/product")
    public String product(Model model) {
        model.addAttribute("products", productService.getAllProducts(new Sort(Sort.Direction.ASC, "id")));
        return "admin/product";
    }

    @RequestMapping(value = "admin/product/add")
    public String addProduct(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product_add";
    }

    @RequestMapping(value = "admin/product/add", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {

        // Validate product form fields
        validator.validate(product, bindingResult);

        // If any errors return product_add html page
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/product_add";
        }

        // Add product to database
        productService.addProduct(product);

        return "redirect:/admin/product";

    }

    @RequestMapping(value = "admin/product/edit/{pid}")
    public String editProduct(@PathVariable(value = "pid") Integer pid, Model model) {

        model.addAttribute("product", productService.findById(pid.longValue()));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product_edit";
    }

    @RequestMapping(value = "admin/product/edit/{pid}", method = RequestMethod.POST)
    public String editProductPost(@ModelAttribute("product") Product product, @PathVariable("pid") Integer pid, Model model, BindingResult bindingResult) {

        product.setId(pid.longValue());

        validator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("cateogires", categoryService.getAllCategories());
            return "admin/product_edit";
        }

        productService.updateProduct(product);

        return "redirect:/admin/product";
    }
}
