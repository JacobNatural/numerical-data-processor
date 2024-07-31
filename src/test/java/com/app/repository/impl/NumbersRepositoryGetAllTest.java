package com.app.repository.impl;

import com.app.extension.repository.NumbersRepositoryExtension;
import com.app.numbers_service.Keys;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

@ExtendWith(NumbersRepositoryExtension.class)
@RequiredArgsConstructor
public class NumbersRepositoryGetAllTest {
    private final NumbersRepository numbersRepository;

    @Test
    @DisplayName("When the number repository retrieves all data correctly")
    public void test1(){

        Assertions.assertThat(numbersRepository.getAll())
                .isEqualTo(Map.of(
                        Keys.MIN, List.of(12,1,12),
                        Keys.MID, List.of(22,2,13),
                        Keys.MAX,List.of(32,4,24)));
    }
}
