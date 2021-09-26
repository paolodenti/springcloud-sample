package com.github.paolodenti.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "products")
public class ConfigServerClientConfig {

    private String a;

    private String secret;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
