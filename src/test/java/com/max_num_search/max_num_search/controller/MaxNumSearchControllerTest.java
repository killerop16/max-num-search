package com.max_num_search.max_num_search.controller;

import com.max_num_search.max_num_search.service.MaxNumSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class MaxNumSearchControllerTest {

    @Mock
    private MaxNumSearchService maxNumSearchService;

    @InjectMocks
    private MaxNumSearchController maxNumSearchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(maxNumSearchController).build();
    }

    @Test
    void testFindMaxNumber() throws Exception {
        // Мокаем сервис
        when(maxNumSearchService.findMaxNumber("test-file.xlsx", 2)).thenReturn(40);

        // Отправляем GET запрос и проверяем ответ
        mockMvc.perform(get("/api/v1/find-max-num")
                        .param("filePath", "test-file.xlsx")
                        .param("number", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("40"));  // Проверка, что вернулся правильный результат
    }
}
