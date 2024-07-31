package com.app.txt.load.generic;

import com.app.parser.LineParser;
import com.app.txt.transfer.Transfer;
import com.app.txt.load.Load;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Abstract base class for loading data from a file.
 * Implements the {@link Load} interface and provides a common implementation for reading data
 * using a {@link Transfer} and a {@link LineParser}.
 *
 * @param <T> the type of keys used in the data.
 * @param <U> the type of values associated with the keys.
 */
@AllArgsConstructor
public abstract class AbstractLoad<T, U> implements Load<T, U> {

    private final Transfer<T, U> transfer;
    private final LineParser<T, U> lineParser;

    /**
     * Reads data from the specified file and parses it into a map of keys and values.
     * Uses the provided {@link Transfer} to handle the file reading and the {@link LineParser}
     * to parse each line of the file into key-value pairs.
     *
     * @param filename the name of the file to read from.
     * @return a map where the keys are of type {@code T} and the values are lists of items of type {@code U}.
     */
    @Override
    public Map<T, List<U>> read(String filename) {
        return transfer.read(filename, lineParser);
    }
}