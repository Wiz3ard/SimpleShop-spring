package me.kamil.simpleshop.service.impl;

import me.kamil.simpleshop.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kamil on 2017-04-16.
 */
@Service
public class OrderValidator implements Validator {

    public static final String POSTAL_CODE_PATTERN = "^[0-9]{2}-[0-9]{3}$";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> c) {
        return Order.class.equals(c);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;

        if (order == null) {
            return;
        }

        ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "city", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "postalCode", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "deliveryAdress", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "NotEmpty");

        pattern = Pattern.compile(POSTAL_CODE_PATTERN);
        matcher = pattern.matcher(order.getPostalCode());

        if (!matcher.matches()) {
            errors.rejectValue("postalCode", "Order.PostalCode.Pattern");
        }

        if (order.getPhoneNumber().length() != 9) {
            errors.rejectValue("phoneNumber", "Order.PhoneNumber.Length");
        }

        if (order.getPhoneNumber().matches("^-?\\\\d+$")) {
            errors.rejectValue("phoneNumber", "Order.PhoneNumber.Pattern");
        }
    }
}
