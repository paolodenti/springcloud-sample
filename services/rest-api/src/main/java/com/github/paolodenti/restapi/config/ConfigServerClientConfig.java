package com.github.paolodenti.restapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest-api")
public class ConfigServerClientConfig {

    private String theProperty;

    public String getTheProperty() {
        return theProperty;
    }

    public void setTheProperty(String theProperty) {
        this.theProperty = theProperty;
    }
}
