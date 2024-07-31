package com.app.data_provider;

import com.app.numbers_service.Keys;
import lombok.SneakyThrows;
import org.junit.jupiter.params.provider.Arguments;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface DataProvider {
    String FILENAME_LOAD = "transfer_load.txt";
    String NUMBERS_FILENAME_LOAD  = "numbers_load.txt";
    String FILENAME_SAVE = "transfer_save.txt";
    String SIMPLE_TEXT = "simple text";

    @SneakyThrows
    static String getTransferFilenameLoadPath(){

        return Paths.get(DataProvider.class.getClassLoader().getResource(FILENAME_LOAD).toURI()).toString();
    }

    @SneakyThrows
    static String getNumbersLoadTxtPath(){

        return Paths.get(DataProvider.class.getClassLoader().getResource(NUMBERS_FILENAME_LOAD).toURI()).toString();
    }

    Map<Integer, List<Integer>> getNumbers =
            Map.of(1,List.of(3,4,18),
                    2, List.of(10,30,33),
                    3, List.of(37,50,80),
                    4,List.of(3,4,8),
                    5,List.of(10,39,33),
                    6,List.of(37,50,60),
                    7, List.of(3,4,18),
                    8,List.of(18,39,33),
                    9, List.of(37,50,60));



    static Stream<Arguments> provideDataNoPerfectNumbers(){
        return Stream.of(
                Arguments.of(
                        getNumbers.get(1),
                        getNumbers.get(2),
                        getNumbers.get(3),
                        Map.of(Keys.MIN, List.of(18), Keys.MID, List.of())
                ),
                Arguments.of(
                        getNumbers.get(4),
                        getNumbers.get(5),
                        getNumbers.get(6),
                        Map.of(Keys.MIN, List.of(), Keys.MID, List.of(39))
                ),
                Arguments.of(
                        getNumbers.get(7),
                        getNumbers.get(8),
                        getNumbers.get(9),
                        Map.of(Keys.MIN, List.of(18), Keys.MID, List.of(39))
                )
        );
    }

    static Stream<Arguments> provideDataDivisibleMaxListByDifferentBetweenMaxMinAndMinMid(){
        return Stream.of(
                Arguments.of(
                        getNumbers.get(1),
                        getNumbers.get(2),
                        getNumbers.get(3),
                        1)
                ,
                Arguments.of(
                        getNumbers.get(4),
                        getNumbers.get(5),
                        getNumbers.get(6),
                       2)
                ,
                Arguments.of(
                        getNumbers.get(7),
                        getNumbers.get(8),
                        getNumbers.get(9),
                        0)
        );
    }

}
