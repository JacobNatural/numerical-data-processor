package com.app.validator;

import java.util.List;

/**
 * A generic interface for validating objects of type {@code T}.
 * Implementations of this interface should provide validation logic for specific types.
 *
 * @param <T> the type of object to be validated.
 */
public interface Validator<T> {

    /**
     * Validates the provided object and returns a list of validation error messages.
     * If the object is valid, an empty list should be returned.
     *
     * @param t the object to validate.
     * @return a list of validation error messages. If the object is valid, returns an empty list.
     */
    List<String> validate(T t);

    /**
     * Validates the provided object using the specified {@link Validator}.
     * If validation errors are found, an {@link IllegalArgumentException} is thrown with the error messages.
     *
     * @param t the object to validate.
     * @param validator the {@link Validator} instance used to validate the object.
     * @param <T> the type of object being validated.
     * @throws IllegalArgumentException if validation errors are found.
     */
    static <T> void validate(T t, Validator<T> validator) {
        var errors = validator.validate(t);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}