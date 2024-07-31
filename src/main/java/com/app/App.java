package com.app;

import com.app.numbers_service.Keys;
import com.app.numbers_service.impl.NumbersServiceImpl;
import com.app.parser.impl.NumbersLineParser;
import com.app.repository.impl.NumbersRepository;
import com.app.txt.load.impl.NumbersLoad;
import com.app.txt.transfer.impl.TransferImpl;
import com.app.validator.impl.NumbersValidator;

import java.util.Comparator;

/**
 * The entry point of the application. This class sets up the components required to process
 * and analyze numerical data from a file and prints the results of various operations.
 */
public class App {
    public static void main(String[] args) {

        // TXT SETUP FOR READ
        var filename = "numbers.txt";
        var numberTransfer = new TransferImpl<Keys, Integer>();
        var numberValidator = new NumbersValidator();
        var numberLineParser = new NumbersLineParser("([1-9][0-9]*;){2}[1-9][0-9]*", numberValidator);
        var numbersLoad = new NumbersLoad(numberTransfer, numberLineParser);

        // REPOSITORY SETUP
        var numbersRepository = new NumbersRepository(filename, numbersLoad);

        // SERVICE SETUP
        var numbers = new NumbersServiceImpl(numbersRepository);

        // METHODS OF SERVICE
        System.out.println("Is the data perfect?");
        System.out.println(numbers.isPerfect() + "\n");

        System.out.println("Numbers needed to remove to make the data perfect:");
        System.out.println(numbers.numbersNeedToRemoveForPerfectData() + "\n");

        System.out.println("Count of numbers in the max list that are divisible by the difference between max-min and min-mid lists:");
        System.out.println(numbers.howManyNumberInMaxListWhichAreDivisibleByDifferentBetweenMaxNumberMinListAndMinNumberMidList() + "\n");

        System.out.println("Keys with the longest non-decreasing arithmetic sequences:");
        System.out.println(numbers.findKeysWithLongestNonDecreasingArithmeticSequence() + "\n");

        System.out.println("Indices with the smallest and largest differences between the lists:");
        System.out.println(numbers.getIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedLists(Comparator.naturalOrder()) + "\n");
    }
}