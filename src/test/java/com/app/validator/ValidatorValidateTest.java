package com.app.validator;


import com.app.extension.validate.NumbersValidatorExtension;
import com.app.validator.impl.NumbersValidator;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(NumbersValidatorExtension.class)
@RequiredArgsConstructor
public class ValidatorValidateTest {
    private final NumbersValidator numbersValidator;

    @Test
    @DisplayName("When numbers are not valid")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                Validator.validate(List.of(7,12,10), numbersValidator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Numbers are wrong");
    }

    @Test
    @DisplayName("When numbers are not valid")
    public void test2(){

        Assertions.assertThatNoException().isThrownBy(() ->
                        Validator.validate(List.of(7,12,16), numbersValidator));

    }
}
