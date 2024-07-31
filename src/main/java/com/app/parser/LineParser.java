package com.app.parser;

import java.util.Map;

/**
 * Interface for parsing lines of text into a map of keys and values.
 *
 * @param <T> the type of keys in the resulting map.
 * @param <U> the type of values in the resulting map.
 */
public interface LineParser<T, U> {

    /**
     * Parses a line of text into a map of keys and values.
     *
     * @param line the line of text to parse.
     * @return a map containing keys and their corresponding values extracted from the line.
     * @throws IllegalArgumentException if the line is invalid or cannot be parsed.
     */
    Map<T, U> parse(String line);
}