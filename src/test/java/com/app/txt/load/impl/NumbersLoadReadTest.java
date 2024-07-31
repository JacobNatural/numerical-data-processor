package com.app.txt.load.impl;

import com.app.extension.txt.NumbersLoadExtension;
import com.app.numbers_service.Keys;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.getNumbersLoadTxtPath;

@ExtendWith({NumbersLoadExtension.class})
@RequiredArgsConstructor
public class NumbersLoadReadTest {

    private final NumbersLoad numbersLoad;

    @Test
    @DisplayName("When you load numbers correctly from the file")
    public void test1(){

        Assertions.assertThat(numbersLoad.read(getNumbersLoadTxtPath()))
                .isEqualTo(Map.of(
                        Keys.MIN, List.of(12,1,12),
                        Keys.MID, List.of(22,2,13),
                        Keys.MAX, List.of(32,4,24)));
    }
}
