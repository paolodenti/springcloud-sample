package com.github.paolodenti.service1.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public Map<Long, String> customers() {
        return Map.ofEntries(Map.entry(1L, "Mark"), Map.entry(2L, "John"));
    }
}
