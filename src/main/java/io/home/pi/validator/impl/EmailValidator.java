package io.home.pi.validator.impl;

import io.home.pi.validator.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.validator
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 09:21
 */

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        return (validateEmail(username));
    }

    private boolean validateEmail(final String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}