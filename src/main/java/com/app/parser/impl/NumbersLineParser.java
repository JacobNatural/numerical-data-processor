package com.app.parser.impl;

import com.app.numbers_service.Keys;
import com.app.parser.LineParser;
import com.app.validator.Validator;
import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Implementation of the LineParser interface for parsing lines of numbers.
 * Parses a line of text into a map of keys and integer values.
 */
@AllArgsConstructor
public class NumbersLineParser implements LineParser<Keys, Integer> {

    private final String regex;
    private final Validator<List<Integer>> validator;

    /**
     * Parses a line of text into a map of keys and integer values.
     * The line must match the specified regex pattern and pass the provided validator.
     *
     * @param line the line of text to parse.
     * @return a map containing keys and their corresponding integer values.
     * @throws IllegalArgumentException if the line is null, empty, or invalid.
     */
    @Override
    public Map<Keys, Integer> parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Line cannot be empty");
        }

        if (!line.matches(regex)) {
            throw new IllegalArgumentException("Line is invalid");
        }

        var split = line.split(";");

        var numbers = Arrays
                .stream(split)
                .map(Integer::parseInt)
                .sorted(Comparator.naturalOrder())
                .toList();

        Validator.validate(numbers, validator);

        return new EnumMap<>(Map.of(
                Keys.MIN, numbers.get(0),
                Keys.MID, numbers.get(1),
                Keys.MAX, numbers.get(2)));
    }
}
