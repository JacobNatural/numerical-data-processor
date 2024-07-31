package com.app.txt.transfer.impl;

import com.app.extension.txt.TransferImplExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.getTransferFilenameLoadPath;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplReadTest {
    private final TransferImpl<Long, Integer> transferImpl;

    @Test
    @DisplayName("When the filename is null")
    public void test1(){
        Assertions.assertThatThrownBy(
                () -> transferImpl.read(null, x -> Map.of(1L,2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2(){
        Assertions.assertThatThrownBy(
                        () -> transferImpl.read("", x -> Map.of(1L,2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test3(){
        Assertions.assertThatThrownBy(
                        () -> transferImpl.read(getTransferFilenameLoadPath(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line parser cannot be null");
    }

    @Test
    @DisplayName("When the filename is not correctly")
    public void test4(){

        Assertions.assertThatThrownBy(
                () -> transferImpl.read("wrong_path.txt", x -> Map.of(1L,2)))
                .isInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("When the file was read correctly")
    public void test5(){

        Assertions.assertThat(transferImpl.read(getTransferFilenameLoadPath(), x -> {
            var line = x.split(";");
            return Map.of(Long.parseLong(line[0]),Integer.parseInt(line[1]));
        }))
                .isEqualTo(Map.of(
                1L, List.of(12) ,
                2L, List.of(34,20),
                3L,List.of(1)));
    }
}
