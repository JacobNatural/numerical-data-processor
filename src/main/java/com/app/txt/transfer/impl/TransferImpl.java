package com.app.txt.transfer.impl;

import com.app.parser.LineParser;
import com.app.txt.transfer.Transfer;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link Transfer} interface for reading and writing data.
 * This class provides methods for reading data from a file and writing data to a file,
 * using a {@link LineParser} to parse lines and a {@link Function} to prepare data for writing.
 *
 * @param <T> the type of keys used in the data.
 * @param <U> the type of values associated with the keys.
 */
public class TransferImpl<T, U> implements Transfer<T, U> {

    /**
     * Reads data from the specified file and parses it into a map of keys and values.
     * Uses the provided {@link LineParser} to parse each line of the file into key-value pairs.
     *
     * @param filename the name of the file to read from.
     * @param lineParser the {@link LineParser} instance used to parse lines into key-value pairs.
     * @return a map where the keys are of type {@code T} and the values are lists of items of type {@code U}.
     * @throws IllegalArgumentException if filename or lineParser is null or filename is empty.
     */
    @Override
    @SneakyThrows
    public Map<T, List<U>> read(String filename, LineParser<T, U> lineParser) {

        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        if (lineParser == null) {
            throw new IllegalArgumentException("Line parser cannot be null");
        }

        try (var lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(lineParser::parse)
                    .flatMap(line -> line.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            number -> new ArrayList<>(List.of(number.getValue())),
                            (v1, v2) -> {
                                v1.addAll(v2);
                                return v1;
                            }));
        }
    }

    /**
     * Writes data to the specified file using the provided function to prepare the data for writing.
     *
     * @param filename the name of the file to write to.
     * @param t the data to write.
     * @param prepare a {@link Function} that converts the data into a string format suitable for writing.
     * @throws IllegalArgumentException if filename, data, or prepare function is null or filename is empty.
     */
    @SneakyThrows
    @Override
    public void write(String filename, T t, Function<T, String> prepare) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        if (t == null) {
            throw new IllegalArgumentException("T cannot be null");
        }

        if (prepare == null) {
            throw new IllegalArgumentException("Prepare cannot be null");
        }

        try (var fw = new FileWriter(filename); var pw = new PrintWriter(fw)) {
            pw.println(prepare.apply(t));
        }
    }
}