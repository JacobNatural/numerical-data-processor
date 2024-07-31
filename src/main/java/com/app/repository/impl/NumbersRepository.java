package com.app.repository.impl;

import com.app.numbers_service.Keys;
import com.app.repository.Repository;
import com.app.txt.load.impl.NumbersLoad;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Implementation of the Repository interface for managing collections of numbers.
 * This class retrieves numbers from a file and organizes them by categories defined by the Keys enum.
 */
@ToString
public class NumbersRepository implements Repository<Keys, Integer> {

    private final Map<Keys, List<Integer>> numbersByCategory;

    /**
     * Constructs a NumbersRepository with the specified filename and NumbersLoad instance.
     * The NumbersLoad instance is used to read and load the numbers from the file into the repository.
     *
     * @param filename the name of the file from which to read the numbers.
     * @param numbersLoad the NumbersLoad instance used to read the file.
     * @throws IllegalArgumentException if numbersLoad is null.
     */
    public NumbersRepository(String filename, NumbersLoad numbersLoad) {
        if (numbersLoad == null) {
            throw new IllegalArgumentException("NumbersLoad cannot be null");
        }
        this.numbersByCategory = numbersLoad.read(filename);
    }

    /**
     * Retrieves all the numbers organized by their respective keys.
     *
     * @return a map where the keys are of type {@link Keys} and the values are lists of integers associated with each key.
     */
    @Override
    public Map<Keys, List<Integer>> getAll() {
        return numbersByCategory;
    }

    /**
     * Retrieves the list of numbers associated with the specified key.
     *
     * @param key the key for which to retrieve the list of numbers.
     * @return a list of integers associated with the specified key, or null if the key is not found.
     */
    public List<Integer> getNumbersByKey(Keys key) {
        return numbersByCategory.get(key);
    }
}