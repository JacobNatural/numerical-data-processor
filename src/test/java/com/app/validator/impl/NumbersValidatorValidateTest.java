package com.app.validator.impl;

import com.app.extension.validate.NumbersValidatorExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(NumbersValidatorExtension.class)
@RequiredArgsConstructor
public class NumbersValidatorValidateTest {
    private final NumbersValidator numbersValidator;

    @Test
    @DisplayName("When the numbers are not valid")
    public void test1(){

        Assertions.assertThat(
                numbersValidator.validate(List.of(5,9,5)))
                .isEqualTo(List.of("Numbers are wrong"));
    }

    @Test
    @DisplayName("When the numbers are correct")
    public void test2(){

        Assertions.assertThat(
                        numbersValidator.validate(List.of(5,9,15)))
                .isEqualTo(List.of());
    }
}
