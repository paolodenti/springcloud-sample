package com.github.paolodenti.service1.web;

import com.github.paolodenti.service1.config.ConfigServerClientConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ConfigServerClientConfig clientConfig;

    public TestController(ConfigServerClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @GetMapping
    public Map<String, String> test() {
        return Map.of("a", clientConfig.getA(), "secret", clientConfig.getSecret());
    }
}
