package com.app.parser.impl;

import com.app.extension.parser.NumbersLineParserExtension;
import com.app.numbers_service.Keys;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(NumbersLineParserExtension.class)
@RequiredArgsConstructor
public class NumbersLineParserParseTest {

    private final NumbersLineParser parser;

    @Test
    @DisplayName("When the line is null")
    public void test1(){

        Assertions.assertThatThrownBy(
                () -> parser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the line is empty")
    public void test2(){

        Assertions.assertThatThrownBy(
                        () -> parser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    @Test
    @DisplayName("When the line is invalid")
    public void test3(){

        Assertions.assertThatThrownBy(
                        () -> parser.parse("33a;32;13"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line is invalid");
    }

    @Test
    @DisplayName("When numbers are invalid")
    public void test4(){

        Assertions.assertThatThrownBy(() -> parser.parse("30;30;54"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Numbers are wrong");
    }

    static Stream<Arguments> dataProvide(){
        return Stream.of(
                Arguments.of("30;35;54", Map.of(Keys.MIN, 30, Keys.MID, 35, Keys.MAX, 54)),
                Arguments.of("40;3;9", Map.of(Keys.MIN, 3, Keys.MID, 9, Keys.MAX, 40)),
                Arguments.of("55;45;24", Map.of(Keys.MIN, 24, Keys.MID, 45, Keys.MAX, 55)),
                Arguments.of("9;5;3", Map.of(Keys.MIN, 3, Keys.MID, 5, Keys.MAX, 9))
        );
    }

    @ParameterizedTest
    @DisplayName("When the parser parse correctly")
    @MethodSource("dataProvide")
    public void test5(String line, Map<Keys, Integer> expectedResult){

        Assertions.assertThat( parser.parse(line))
                .isEqualTo(expectedResult);
    }


}
