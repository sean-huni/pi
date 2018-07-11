package io.home.pi.validator.impl;

import io.home.pi.validator.PasswordMatch;
import io.home.pi.web.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.validator.impl
 * USER      : sean
 * DATE      : 10-July-2018
 * TIME      : 19:52
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final UserDTO user = (UserDTO) value;
        return user.getPass().equals(user.getPass2());
    }
}
