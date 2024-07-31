package com.app.numbers_service.impl;

import com.app.numbers_service.Keys;
import com.app.repository.impl.NumbersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class NumbersServiceImplIsPerfectTest {

    @Mock
    private NumbersRepository numbersRepository;

    @InjectMocks
    private NumbersServiceImpl numbersService;

    @Test
    @DisplayName("When numbers are perfect")
    public void test1(){
        Mockito.when(numbersRepository.getNumbersByKey(Keys.MIN))
                .thenReturn(List.of(3,4,8));

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MID))
                .thenReturn(List.of(10,25,33));

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MAX))
                .thenReturn(List.of(37,50,60));

        Assertions.assertThat(numbersService.isPerfect())
                .isTrue();

        InOrder inOrder = Mockito.inOrder(numbersRepository);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MIN);
        inOrder.verify(numbersRepository, Mockito.calls(2)).getNumbersByKey(Keys.MID);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MAX);
    }


    @ParameterizedTest
    @DisplayName("When numbers are not perfect")
    @MethodSource("com.app.data_provider.DataProvider#provideDataNoPerfectNumbers")
    public void test2(List<Integer> numbersMin, List<Integer> numbersMid, List<Integer> numbersMax ){
        Mockito.when(numbersRepository.getNumbersByKey(Keys.MIN))
                .thenReturn(numbersMin);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MID))
                .thenReturn(numbersMid);

        Mockito.when(numbersRepository.getNumbersByKey(Keys.MAX))
                .thenReturn(numbersMax);

        Assertions.assertThat(numbersService.isPerfect())
                .isFalse();

        InOrder inOrder = Mockito.inOrder(numbersRepository);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MIN);
        inOrder.verify(numbersRepository, Mockito.calls(2)).getNumbersByKey(Keys.MID);
        inOrder.verify(numbersRepository, Mockito.calls(1)).getNumbersByKey(Keys.MAX);
    }
}
