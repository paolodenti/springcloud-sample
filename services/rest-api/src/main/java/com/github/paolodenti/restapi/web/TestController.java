package com.github.paolodenti.restapi.web;

import com.github.paolodenti.restapi.config.ConfigServerClientConfig;
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
        return Map.of("theProperty", clientConfig.getTheProperty());
    }
}
