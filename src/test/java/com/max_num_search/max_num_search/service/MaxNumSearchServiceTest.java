package com.max_num_search.max_num_search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaxNumSearchServiceTest {

    @Mock
    private XlsxFileReaderService xlsxFileReaderService;

    @InjectMocks
    private MaxNumSearchService maxNumSearchService;

    private List<Integer> mockNumbers;

    @BeforeEach
    void setUp() {
        mockNumbers = Arrays.asList(10, 20, 30, 40, 50); // Пример чисел
    }

    @Test
    void testFindMaxNumber() throws IOException {
        // Мокаем вызов метода чтения из файла
        when(xlsxFileReaderService.readNumbersFromFile("test-file.xlsx")).thenReturn(mockNumbers);

        // Тестируем метод поиска 2-го максимального числа
        int result = maxNumSearchService.findMaxNumber("test-file.xlsx", 2);

        // Проверяем, что вернулось правильное максимальное число
        assertEquals(40, result);
    }

    @Test
    void testFindMaxNumber_IllegalArgument() throws IOException {
        // Мокаем вызов метода чтения из файла
        when(xlsxFileReaderService.readNumbersFromFile("test-file.xlsx")).thenReturn(mockNumbers);

        // Тестируем случай, когда N больше размера списка
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            maxNumSearchService.findMaxNumber("test-file.xlsx", 10);
        });

        assertEquals("N больше количества чисел в файле.", thrown.getMessage());
    }
}
