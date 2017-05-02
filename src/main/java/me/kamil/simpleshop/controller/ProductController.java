package me.kamil.simpleshop.controller;

import me.kamil.simpleshop.auth.service.UserService;
import me.kamil.simpleshop.domain.CartProduct;
import me.kamil.simpleshop.domain.Order;
import me.kamil.simpleshop.domain.Product;
import me.kamil.simpleshop.domain.ShoppingCart;
import me.kamil.simpleshop.service.OrderService;
import me.kamil.simpleshop.service.ProductService;
import me.kamil.simpleshop.service.ShoppingCartService;
import me.kamil.simpleshop.service.impl.OrderValidator;
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

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    OrderValidator orderValidator;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/product/{pid}")
    public String productDetails(@PathVariable("pid") Integer pid, Model model) {
        Product p = productService.findById(pid.longValue());

        if (p == null) return "redirect:/home";

        model.addAttribute("product", p);

        return "product_detail";
    }

    @RequestMapping(value = "/addToCart")
    public String addToCart(@ModelAttribute(value = "amountValue") Integer amount, @ModelAttribute("pid") Integer pid, HttpServletRequest request) {

        CartProduct cartProduct = new CartProduct();
        Product product = productService.findById(pid.longValue());

        if (pid == null) return "redirect:/home";
        if (product == null) return "redirect:/home";

        if (amount == null)
            cartProduct.setAmount(1);
        else
            cartProduct.setAmount(amount);

        if (product.getAmount() < cartProduct.getAmount()) {
            return "redirect:" + request.getHeader("Referer");
        }
        cartProduct.setProduct(product);
        shoppingCartService.addToCart(cartProduct);

        return "redirect:/home";
    }

    @RequestMapping(value = "/cart")
    public String shoppingCart(Model model) {

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
        model.addAttribute("cart", shoppingCart);
        return "cart";

    }

    @RequestMapping(value = "/order")
    public String order(Model model, Principal principal) {

        Order order = new Order();
        order.setUser(userService.findByUserName(principal.getName()));
        order.setProductList(shoppingCartService.getShoppingCart().getProductList());
        model.addAttribute("orderForm", new Order());

        return "order_adress";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderPost(Model model, @ModelAttribute("orderForm") Order orderForm, BindingResult bindingResult) {

        Order order = orderForm;
        orderValidator.validate(orderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "order_adress";
        }
        orderService.addOrder(order);
        return "order_summary";
    }


}
