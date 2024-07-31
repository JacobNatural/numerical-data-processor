package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.numbers_service.Numbers;
import com.app.repository.impl.NumbersRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * Implementation of the Numbers service interface.
 * Provides various operations on number lists stored in the repository.
 */
@RequiredArgsConstructor
@ToString
public class NumbersServiceImpl implements Numbers {

    private final NumbersRepository numbersRepository;

    /**
     * Checks if the data is perfect based on certain conditions.
     *
     * @return true if the data is perfect, false otherwise.
     */
    public boolean isPerfect() {
        var minListMax = minMax(numbersRepository.getNumbersByKey(Keys.MIN), Comparator.naturalOrder());
        var midListMin = minMax(numbersRepository.getNumbersByKey(Keys.MID), Comparator.reverseOrder());
        var midListMax = minMax(numbersRepository.getNumbersByKey(Keys.MID), Comparator.naturalOrder());
        var maxListMin = minMax(numbersRepository.getNumbersByKey(Keys.MAX), Comparator.reverseOrder());

        return (minListMax < midListMin) && (midListMax < maxListMin);
    }

    /**
     * Identifies the numbers that need to be removed to make the data perfect.
     *
     * @return a map containing keys and the corresponding lists of numbers to be removed.
     */
    public Map<Keys, List<Integer>> numbersNeedToRemoveForPerfectData() {
        var minMidList = minMax(numbersRepository.getNumbersByKey(Keys.MID), Comparator.reverseOrder());
        var minMaxList = minMax(numbersRepository.getNumbersByKey(Keys.MAX), Comparator.reverseOrder());

        var firstList = numbersRepository
                .getNumbersByKey(Keys.MIN)
                .stream()
                .filter(number -> number >= minMidList)
                .toList();

        var secondList = numbersRepository
                .getNumbersByKey(Keys.MID)
                .stream()
                .filter(number -> number >= minMaxList)
                .toList();

        return Map.of(Keys.MIN, firstList, Keys.MID, secondList);
    }

    /**
     * Counts the numbers in the max list that are divisible by the difference
     * between the max number of the min list and the min number of the mid list.
     *
     * @return the count of numbers in the max list that meet the condition.
     */
    public int howManyNumberInMaxListWhichAreDivisibleByDifferentBetweenMaxNumberMinListAndMinNumberMidList() {
        return numbersInListWhichAreDivisibleByDifferentBetweenMaxFirstListMinSecondList(
                numbersRepository.getNumbersByKey(Keys.MIN),
                numbersRepository.getNumbersByKey(Keys.MID),
                numbersRepository.getNumbersByKey(Keys.MAX)).size();
    }

    /**
     * Finds keys with the longest non-decreasing arithmetic sequences.
     *
     * @return a list of keys with the longest non-decreasing arithmetic sequences.
     */
    public List<Keys> findKeysWithLongestNonDecreasingArithmeticSequence() {
        return createMapOfKeysAndLongestNonDecreasingSequences(numbersRepository.getAll(), (x, y) -> x >= y)
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        map -> map.getValue().size(),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    /**
     * Finds the indices with the smallest and largest differences between sorted lists.
     *
     * @param comparator the comparator to use for sorting.
     * @return a map containing the indices with the smallest and largest differences.
     * @throws IllegalArgumentException if the comparator is null.
     */
    public Map<String, Integer> getIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedLists(Comparator<Integer> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        var min = getSortedNumbers(numbersRepository.getNumbersByKey(Keys.MIN), comparator);
        var mid = getSortedNumbers(numbersRepository.getNumbersByKey(Keys.MID), comparator);
        var max = getSortedNumbers(numbersRepository.getNumbersByKey(Keys.MAX), comparator);

        var indexSmallest = -1;
        var indexBiggest = -1;
        var differentBiggest = Integer.MIN_VALUE;
        var differentSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < min.size(); i++) {
            var differentTemp = Math.abs(min.get(i) - mid.get(i)) + Math.abs(mid.get(i) - max.get(i));

            if (differentTemp < differentSmallest) {
                differentSmallest = differentTemp;
                indexSmallest = i;
            }

            if (differentTemp > differentBiggest) {
                differentBiggest = differentTemp;
                indexBiggest = i;
            }
        }

        return Map.of(
                "Index with smallest different", indexSmallest,
                "Index with biggest different", indexBiggest);
    }

    /**
     * Finds the minimum or maximum value in a list of numbers based on the provided comparator.
     *
     * @param numbers    the list of numbers.
     * @param comparator the comparator to determine the min/max value.
     * @return the min or max value.
     * @throws NoSuchElementException if the list is empty.
     */
    private int minMax(List<Integer> numbers, Comparator<Integer> comparator) {
        return numbers
                .stream()
                .max(comparator)
                .orElseThrow();
    }

    /**
     * Finds numbers in the max list that are divisible by the difference between the max number
     * of the first list and the min number of the second list.
     *
     * @param minNumbers the first list of numbers.
     * @param midNumbers the second list of numbers.
     * @param maxNumbers the third list of numbers.
     * @return a list of numbers from the max list that meet the condition.
     */
    private List<Integer> numbersInListWhichAreDivisibleByDifferentBetweenMaxFirstListMinSecondList(
            List<Integer> minNumbers, List<Integer> midNumbers, List<Integer> maxNumbers) {

        var different = differentBetweenMaxFirstListMinSecondList(minNumbers, midNumbers);

        if (different != 0) {
            return maxNumbers
                    .stream()
                    .filter(number -> number % different == 0)
                    .toList();
        }
        return List.of();
    }

    /**
     * Calculates the absolute difference between the max number of the first list
     * and the min number of the second list.
     *
     * @param firstList  the first list of numbers.
     * @param secondList the second list of numbers.
     * @return the absolute difference.
     */
    private int differentBetweenMaxFirstListMinSecondList(List<Integer> firstList, List<Integer> secondList) {
        var maxKey1 = minMax(firstList, Comparator.naturalOrder());
        var minKey2 = minMax(secondList, Comparator.reverseOrder());

        return Math.abs(maxKey1 - minKey2);
    }

    /**
     * Creates a map of keys and their longest non-decreasing sequences.
     *
     * @param numbersByKey a map of keys and their associated lists of numbers.
     * @param predicate    the predicate to determine if a sequence is non-decreasing.
     * @return a map of keys and their longest non-decreasing sequences.
     */
    private Map<Keys, List<Integer>> createMapOfKeysAndLongestNonDecreasingSequences(Map<Keys, List<Integer>> numbersByKey, BiPredicate<Integer, Integer> predicate) {
        return new EnumMap<>(Map.of(
                Keys.MIN, findLongestNonDecreasingSequence(numbersByKey.get(Keys.MIN), predicate),
                Keys.MID, findLongestNonDecreasingSequence(numbersByKey.get(Keys.MID), predicate),
                Keys.MAX, findLongestNonDecreasingSequence(numbersByKey.get(Keys.MAX), predicate)));
    }

    /**
     * Finds the longest non-decreasing sequence in a list of numbers based on the provided predicate.
     *
     * @param numbers   the list of numbers.
     * @param predicate the predicate to determine if a sequence is non-decreasing.
     * @return the longest non-decreasing sequence.
     */
    private List<Integer> findLongestNonDecreasingSequence(List<Integer> numbers, BiPredicate<Integer, Integer> predicate) {
        var currentSequence = new ArrayList<Integer>();
        var longestSequence = new ArrayList<Integer>();

        currentSequence.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (predicate.test(numbers.get(i), numbers.get(i - 1))) {
                currentSequence.add(numbers.get(i));
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(numbers.get(i));
            }
        }

        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }

        return longestSequence;
    }

    /**
     * Returns a sorted list of numbers based on the provided comparator.
     *
     * @param numbers    the list of numbers to sort.
     * @param comparator the comparator to determine the sort order.
     * @return the sorted list of numbers.
     */
    private List<Integer> getSortedNumbers(List<Integer> numbers, Comparator<Integer> comparator) {
        return numbers
                .stream()
                .sorted(comparator)
                .toList();
    }
}