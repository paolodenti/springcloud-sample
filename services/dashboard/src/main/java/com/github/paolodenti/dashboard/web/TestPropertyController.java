package com.github.paolodenti.dashboard.web;

import com.github.paolodenti.dashboard.config.ConfigServerClientConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test-property")
public class TestPropertyController {

    private final ConfigServerClientConfig clientConfig;

    public TestPropertyController(ConfigServerClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @GetMapping
    public Map<String, String> test() {
        return Map.of("theProperty", clientConfig.getTheProperty());
    }
}
