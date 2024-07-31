package com.app.repository;

import java.util.List;
import java.util.Map;

/**
 * Generic interface for a repository that manages collections of items categorized by keys.
 *
 * @param <T> the type of keys used to categorize the items.
 * @param <U> the type of items stored in the repository.
 */
public interface Repository<T, U> {

    /**
     * Retrieves all the items organized by their respective keys.
     *
     * @return a map where the keys are of type {@code T} and the values are lists of items of type {@code U} associated with each key.
     */
    Map<T, List<U>> getAll();

    /**
     * Retrieves the list of items associated with the specified key.
     *
     * @param t the key for which to retrieve the list of items.
     * @return a list of items of type {@code U} associated with the specified key, or null if the key is not found.
     */
    List<U> getNumbersByKey(T t);
}