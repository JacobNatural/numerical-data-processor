//package com.app.txt.transfer;
//
//import com.app.parser.LineParser;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//
//public interface Transfer<T,U> {
//
//    Map<T,List<U>> read(String filename, LineParser<T,U> lineParser);
//
//    void write(String txt, T t, Function<T, String> prepare);
//}
package com.app.txt.transfer;

import com.app.parser.LineParser;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Interface for transferring data to and from a file.
 * This interface provides methods for reading data from a file into a map of keys and values,
 * and for writing data to a file.
 *
 * @param <T> the type of keys used in the data.
 * @param <U> the type of values associated with the keys.
 */
public interface Transfer<T, U> {

    /**
     * Reads data from the specified file and parses it into a map of keys and values.
     * Uses the provided {@link LineParser} to parse each line of the file into key-value pairs.
     *
     * @param filename the name of the file to read from.
     * @param lineParser the {@link LineParser} instance used to parse lines into key-value pairs.
     * @return a map where the keys are of type {@code T} and the values are lists of items of type {@code U}.
     */
    Map<T, List<U>> read(String filename, LineParser<T, U> lineParser);

    /**
     * Writes data to the specified file using the provided function to prepare the data for writing.
     * The data is converted into a string format suitable for writing by applying the provided function.
     *
     * @param filename the name of the file to write to.
     * @param t the data to write.
     * @param prepare a {@link Function} that converts the data into a string format suitable for writing.
     */
    void write(String filename, T t, Function<T, String> prepare);
}