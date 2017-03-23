package me.kamil.simpleshop.product.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import me.kamil.simpleshop.product.domain.Product;

@Service
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        return Product.class.equals(c);
    }

    @Override
    public void validate(Object obj, Errors error) {
        Product product = (Product) obj;

        if (product == null) {
            return;
        }

        // Name validations
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", "NotEmpty");

        if (product.getName().length() > 15) {
            error.rejectValue("name", "Product.nameSize");
        }

        //Price Validation
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "price", "NotEmpty");

        if (product.getPrice() < 0) {
            error.rejectValue("price", "Product.priceLowerThanZero");
        }

        //Category Validation
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "category", "Product.wrongCategory");

        //Description Validation
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "description", "NotEmpty");

    }

}