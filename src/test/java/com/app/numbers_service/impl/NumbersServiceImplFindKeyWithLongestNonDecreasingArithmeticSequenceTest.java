package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.repository.impl.NumbersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;


import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class NumbersServiceImplFindKeyWithLongestNonDecreasingArithmeticSequenceTest {

    @Mock
    private NumbersRepository numbersRepository;

    @InjectMocks
    private NumbersServiceImpl numbersService;
    static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                Keys.MIN, List.of(5,1,4,3,7,8,3),
                                Keys.MID, List.of(15,14,24,34,71,18,33),
                                Keys.MAX, List.of(12,1,4,6,7,8,3)
                        ),List.of(Keys.MAX)
                ),
                Arguments.of(
                        Map.of(
                                Keys.MIN, List.of(5,1,4,3,7,8,3),
                                Keys.MID, List.of(15,14,24,34,71,81,33),
                                Keys.MAX, List.of(12,1,4,6,7,8,3)
                        ),List.of(Keys.MID, Keys.MAX)
                ),
                Arguments.of(
                        Map.of(
                                Keys.MIN, List.of(5,1,4,3,7,8,10),
                                Keys.MID, List.of(15,14,24,4,71,1,33),
                                Keys.MAX, List.of(12,1,4,3,7,8,3)
                        ),List.of(Keys.MIN)
                )
        );
    }

    @ParameterizedTest
    @DisplayName("When finding keys with the longest non-decreasing arithmetic sequence correctly")
    @MethodSource("provideData")
    public void test1(Map<Keys, List<Integer>> enterData, List<Keys> expectedResult){

        Mockito.when(numbersRepository.getAll()).thenReturn(enterData);

        Assertions.assertThat(numbersService.findKeysWithLongestNonDecreasingArithmeticSequence())
                .isEqualTo(expectedResult);

        Mockito.verify(numbersRepository, Mockito.times(1)).getAll();
    }
}
