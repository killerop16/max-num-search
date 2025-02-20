package com.max_num_search.max_num_search.controller;

import com.max_num_search.max_num_search.service.MaxNumSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MaxNumSearchController {
    private final MaxNumSearchService maxNumSearchService;

    @GetMapping("/find-max-num")
    public int findMaxNumber(@RequestParam String filePath, @RequestParam int number) {
        return maxNumSearchService.findMaxNumber(filePath, number);
    }

}
