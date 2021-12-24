package com.admin.userservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<ValidName,String> {
    private static final String NAME = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(NAME);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (validateName(s));
    }
    private boolean validateName(final String name) {
        Matcher matcher = PATTERN.matcher(name);
        return matcher.matches();
    }
}
