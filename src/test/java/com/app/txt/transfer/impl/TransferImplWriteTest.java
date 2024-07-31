package com.app.txt.transfer.impl;

import com.app.extension.txt.TransferImplExtension;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplWriteTest {
    private final TransferImpl<String, Integer> transferImpl;

    @Test
    @DisplayName("When the filename is null")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                transferImpl.write(null, "simple text", Function.identity()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2(){

        Assertions.assertThatThrownBy(() ->
                        transferImpl.write("", "simple text", Function.identity()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When the object is null")
    public void test3(){

        Assertions.assertThatThrownBy(() ->
                        transferImpl.write(FILENAME_SAVE, null, Function.identity()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("T cannot be null");
    }

    @Test
    @DisplayName("When the function is null")
    public void test4(){

        Assertions.assertThatThrownBy(() ->
                        transferImpl.write(FILENAME_SAVE, SIMPLE_TEXT, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Prepare cannot be null");
    }

    @Test
    @DisplayName("When you write text to the file correctly")
    @SneakyThrows
    public void test5(){

        transferImpl.write(FILENAME_SAVE, SIMPLE_TEXT, Function.identity());

        try(var lines = Files.lines(Path.of(FILENAME_SAVE))){
            Assertions.assertThat(lines.collect(Collectors.joining("\n")))
                    .isEqualTo(SIMPLE_TEXT);
        }
    }

    @AfterAll
    @SneakyThrows
    public static void cleanData(){
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }
}
