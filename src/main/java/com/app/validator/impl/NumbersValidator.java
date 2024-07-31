package com.app.validator.impl;

import com.app.validator.Validator;

import java.util.List;

/**
 * Implementation of the {@link Validator} interface for validating lists of integers.
 * This validator ensures that the integers in the list are in strictly increasing order.
 */
public class NumbersValidator implements Validator<List<Integer>> {

    /**
     * Validates the provided list of integers to ensure that each number is strictly less than the following number.
     *
     * @param integerList the list of integers to validate.
     * @return a list of validation error messages. Returns a list containing a single error message
     *         if the numbers are not in strictly increasing order; otherwise, returns an empty list.
     */
    @Override
    public List<String> validate(List<Integer> integerList) {
        for (int i = 1; i < integerList.size(); i++) {
            if (integerList.get(i - 1) >= integerList.get(i)) {
                return List.of("Numbers are wrong");
            }
        }
        return List.of();
    }
}