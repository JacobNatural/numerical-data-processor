package com.app.repository.impl;

import com.app.data_provider.DataProvider;
import com.app.extension.txt.NumbersLoadExtension;
import com.app.txt.load.impl.NumbersLoad;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.getNumbersLoadTxtPath;

@ExtendWith(NumbersLoadExtension.class)
@RequiredArgsConstructor
public class NumbersRepositoryTest {
    private final NumbersLoad numbersLoad;

    @Test
    @DisplayName("When the numbers load is null")
    public void test1(){


        Assertions.assertThatThrownBy(
                () -> new NumbersRepository(getNumbersLoadTxtPath(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("NumbersLoad cannot be null");
    }

    @Test
    @DisplayName("When the number repository is initialized correctly")
    public void test2(){

        Assertions.assertThatNoException().isThrownBy(
                        () -> new NumbersRepository(getNumbersLoadTxtPath(), numbersLoad));
    }


}
