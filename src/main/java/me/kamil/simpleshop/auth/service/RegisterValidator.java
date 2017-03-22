package me.kamil.simpleshop.auth.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import me.kamil.simpleshop.auth.domain.User;

@Service
public class RegisterValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> c) {
        return User.class.equals(c);
    }

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(Object obj, Errors error) {
        User user = (User) obj;

        if (user == null)
            return;

        // User name validation
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            error.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUserName(user.getUsername()) != null) {
            error.rejectValue("username", "Duplicate.userForm.username");
        }

        // Password validation

        ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "NotEmpty");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            error.reject("password", "Size.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(error, "passwordConfirm", "NotEmpty");
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            error.rejectValue("passwordConfirm", "Diffrent.userForm.passwordConfirm");
        }

        // EMAIL

        ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "NotEmpty");
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            error.rejectValue("email", "Pattern.userForm.email");
        }
    }

}
