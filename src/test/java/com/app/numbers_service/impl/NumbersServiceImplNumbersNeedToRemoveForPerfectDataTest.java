package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.repository.impl.NumbersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class NumbersServiceImplNumbersNeedToRemoveForPerfectDataTest {

    @Mock
    private NumbersRepository numbersRepository;

    @InjectMocks
    private NumbersServiceImpl numbersService;


    @ParameterizedTest
    @DisplayName("When we remove numbers to be perfect numbers")
    @MethodSource("com.app.data_provider.DataProvider#provideDataNoPerfectNumbers")
    public void test2(
            List<Integer> numbersMin, List<Integer> numbersMid,
            List<Integer> numbersMax, Map<Keys, List<Integer>> expectedResult){

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MIN))
                .thenReturn(numbersMin);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MID))
                .thenReturn(numbersMid);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MAX))
                .thenReturn(numbersMax);

        Assertions.assertThat(numbersService.numbersNeedToRemoveForPerfectData())
                .isEqualTo(expectedResult);

        InOrder inOrder = Mockito.inOrder(numbersRepository);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MID);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MAX);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MIN);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MID);

    }


}
