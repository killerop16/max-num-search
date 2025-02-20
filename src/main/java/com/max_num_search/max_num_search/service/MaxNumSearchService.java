package com.max_num_search.max_num_search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaxNumSearchService {

    private final XlsxFileReaderService xlsxFileReaderService;

    public int findMaxNumber(String filePath, Integer n) {
        log.info("Поиск {}-го максимального числа в файле: {}", n, filePath);

        List<Integer> numbers;
        try {
            numbers = xlsxFileReaderService.readNumbersFromFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (n > numbers.size()) {
            var msg = "N больше количества чисел в файле.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        int nthMax = quickSelect(numbers, 0, numbers.size() - 1, n - 1);
        log.info(n + "-е максимальное число: " + nthMax);
        return nthMax;
    }


    private static int quickSelect(List<Integer> arr, int left, int right, int k) {
        if (left == right) {
            return arr.get(left);
        }

        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) {
            return arr.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private static int partition(List<Integer> arr, int left, int right) {
        int pivot = arr.get(right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr.get(j) > pivot) { // Сортируем по убыванию
                Collections.swap(arr, i, j);
                i++;
            }
        }
        Collections.swap(arr, i, right);
        return i;
    }

}
