package com.app.repository.impl;

import com.app.extension.repository.NumbersRepositoryExtension;
import com.app.numbers_service.Keys;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(NumbersRepositoryExtension.class)
@RequiredArgsConstructor
public class NumbersRepositoryGetNumbersByKeyTest {

    private final NumbersRepository numbersRepository;

    static Stream<Arguments> dataProvide(){
        return Stream.of(
                Arguments.of(Keys.MIN, List.of(12,1,12)),
                Arguments.of(Keys.MID, List.of(22,2,13)),
                Arguments.of(Keys.MAX, List.of(32,4,24))
        );
    }

    @ParameterizedTest
    @DisplayName("When the number repository retrieves data by key correctly")
    @MethodSource("dataProvide")
    public void test1(Keys keys, List<Integer> numbers){

        Assertions.assertThat(numbersRepository.getNumbersByKey(keys))
                .isEqualTo(numbers);

    }
}
