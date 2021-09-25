package com.github.paolodenti.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ConfigserverClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverClientApplication.class, args);
    }

}
