package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.service.CategoryService;
import me.kamil.simpleshop.service.ProductService;
import me.kamil.simpleshop.service.impl.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "admin/product_add";
    }

    @RequestMapping(value = "admin/product/add", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {


        validator.validate(product, bindingResult);


        if (bindingResult.hasErrors()) {
            return "admin/product_add";
        }


        productService.addProduct(product);

        return "redirect:/admin/product";

    }

    @RequestMapping(value = "admin/product/edit/{pid}")
    public String editProduct(@PathVariable(value = "pid") Integer pid, Model model) {

        model.addAttribute("product", productService.findById(pid.longValue()));
        return "admin/product_edit";
    }

    @RequestMapping(value = "admin/product/edit/{pid}", method = RequestMethod.POST)
    public String editProductPost(@ModelAttribute("product") Product product, @PathVariable("pid") Integer pid, Model model, BindingResult bindingResult) {

        product.setId(pid.longValue());

        validator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/product_edit";
        }

        productService.updateProduct(product);

        return "redirect:/admin/product";
    }

    @RequestMapping(value = "admin/product/available/{pid}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Integer pid) {

        Product p = productService.findById(pid.longValue());
        if (p == null) return "redirect:/admin/product";


        productService.setProductAvailable(p);

        return "redirect:/admin/product";
    }

    @RequestMapping(value = "admin/category", method = RequestMethod.GET)
    public String category() {
        return "admin/category";
    }

}
