package com.app.numbers_service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Interface defining the operations for managing and analyzing lists of numbers.
 */
public interface Numbers {

    /**
     * Checks if the data is perfect based on specific conditions.
     *
     * @return true if the data is perfect, false otherwise.
     */
    boolean isPerfect();

    /**
     * Identifies the numbers that need to be removed to make the data perfect.
     *
     * @return a map containing keys and the corresponding lists of numbers to be removed.
     */
    Map<Keys, List<Integer>> numbersNeedToRemoveForPerfectData();

    /**
     * Counts the numbers in the max list that are divisible by the difference
     * between the max number of the min list and the min number of the mid list.
     *
     * @return the count of numbers in the max list that meet the condition.
     */
    int howManyNumberInMaxListWhichAreDivisibleByDifferentBetweenMaxNumberMinListAndMinNumberMidList();

    /**
     * Finds keys with the longest non-decreasing arithmetic sequences.
     *
     * @return a list of keys with the longest non-decreasing arithmetic sequences.
     */
    List<Keys> findKeysWithLongestNonDecreasingArithmeticSequence();

    /**
     * Finds the indices with the smallest and largest differences between sorted lists.
     *
     * @param comparator the comparator to use for sorting.
     * @return a map containing the indices with the smallest and largest differences.
     * @throws IllegalArgumentException if the comparator is null.
     */
    Map<String, Integer> getIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedLists(Comparator<Integer> comparator);
}