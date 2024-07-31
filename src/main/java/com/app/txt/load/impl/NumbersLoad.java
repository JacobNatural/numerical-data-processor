package com.app.txt.load.impl;

import com.app.numbers_service.Keys;
import com.app.parser.LineParser;
import com.app.txt.load.generic.AbstractLoad;
import com.app.txt.transfer.Transfer;
import com.app.txt.load.Load;

/**
 * Concrete implementation of the {@link Load} interface for loading numbers.
 * Inherits from {@link AbstractLoad} and provides a specific implementation for
 * loading and parsing number data using {@link Keys} as keys and {@link Integer} as values.
 */
public class NumbersLoad extends AbstractLoad<Keys, Integer> implements Load<Keys, Integer> {

    /**
     * Constructs a NumbersLoad instance with the specified transfer and line parser.
     *
     * @param transfer the {@link Transfer} instance used for reading data from the file.
     * @param lineParser the {@link LineParser} instance used for parsing lines of the file into key-value pairs.
     */
    public NumbersLoad(Transfer<Keys, Integer> transfer, LineParser<Keys, Integer> lineParser) {
        super(transfer, lineParser);
    }
}