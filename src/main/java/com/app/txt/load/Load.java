package com.app.txt.load;

import java.util.List;
import java.util.Map;

/**
 * Interface for loading data from a file into a map of keys and values.
 *
 * @param <T> the type of keys used in the data.
 * @param <U> the type of values associated with the keys.
 */
public interface Load<T, U> {

    /**
     * Reads data from the specified file and parses it into a map of keys and values.
     *
     * @param filename the name of the file to read from.
     * @return a map where the keys are of type {@code T} and the values are lists of items of type {@code U}.
     */
    Map<T, List<U>> read(String filename);
}