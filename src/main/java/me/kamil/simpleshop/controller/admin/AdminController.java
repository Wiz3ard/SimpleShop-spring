package me.kamil.simpleshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kamil on 2017-05-24.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "admin")
    public String admin() {
        return "admin/admin";
    }
}
