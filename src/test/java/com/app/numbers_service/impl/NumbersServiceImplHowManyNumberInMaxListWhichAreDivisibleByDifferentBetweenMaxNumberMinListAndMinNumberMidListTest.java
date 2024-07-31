package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.repository.impl.NumbersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class NumbersServiceImplHowManyNumberInMaxListWhichAreDivisibleByDifferentBetweenMaxNumberMinListAndMinNumberMidListTest {

    @Mock
    private NumbersRepository numbersRepository;

    @InjectMocks
    private NumbersServiceImpl numbersService;


    @ParameterizedTest
    @DisplayName("When we got the correct number")
    @MethodSource("com.app.data_provider.DataProvider#provideDataDivisibleMaxListByDifferentBetweenMaxMinAndMinMid")
    public void test1(List<Integer> numbersMin, List<Integer> numbersMid,
                      List<Integer> numbersMax, int expectedResult){

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MIN))
                .thenReturn(numbersMin);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MID))
                .thenReturn(numbersMid);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MAX))
                .thenReturn(numbersMax);



        Assertions.assertThat(
                numbersService
                        .howManyNumberInMaxListWhichAreDivisibleByDifferentBetweenMaxNumberMinListAndMinNumberMidList())
                .isEqualTo(expectedResult);

    }
}
