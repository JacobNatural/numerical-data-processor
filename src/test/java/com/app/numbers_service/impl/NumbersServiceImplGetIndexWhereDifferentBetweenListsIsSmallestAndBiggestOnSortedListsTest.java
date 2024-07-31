package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.repository.impl.NumbersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class NumbersServiceImplGetIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedListsTest {

    @Mock
    private NumbersRepository numbersRepository;

    @InjectMocks
    private NumbersServiceImpl numbersService;

    @Test
    @DisplayName("When the comparator is null")
    public void test1(){
        Assertions.assertThatThrownBy(
                () -> numbersService
                        .getIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedLists(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator cannot be null");
    }

    static Stream<Arguments> dataProvide(){
        return Stream.of(
                Arguments.of(List.of(5,1,3,8,10,12,15),
                        List.of(29,30,23,18,20,17,22),
                        List.of(35,40,51,39,54,33,37),4,5
                ),
                Arguments.of(List.of(8,4,2,12,10,14,13),
                        List.of(19,29,25,18,24,28,30),
                        List.of(39,41,31,59,48,53,33),0,6
                ),
                Arguments.of(List.of(9,1,6,2,15,3,13),
                        List.of(29,15,25,28,19,22,23),
                        List.of(49,51,45,39,42,39,43),4,2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("When we got correctly index for find smallest and biggest different of value")
    @MethodSource("dataProvide")
    public void test2(List<Integer> numbersMin, List<Integer> numbersMid,
                      List<Integer> numbersMax, int expectedSmallestIndex, int expectedBiggestIndex){


        Mockito.when(numbersRepository.getNumbersByKey(Keys.MIN))
                .thenReturn(numbersMin);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MID))
                .thenReturn(numbersMid);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MAX))
                .thenReturn(numbersMax);


        Assertions.assertThat(
                        numbersService
                                .getIndexWhereDifferentBetweenListsIsSmallestAndBiggestOnSortedLists(Comparator.naturalOrder()))
                .isEqualTo(Map.of(
                        "Index with smallest different",expectedSmallestIndex,
                        "Index with biggest different",expectedBiggestIndex));

    }
}
