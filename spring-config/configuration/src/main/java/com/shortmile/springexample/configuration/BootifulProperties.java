package com.shortmile.springexample.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding     //bind property to the constructor instead of setter.
@ConfigurationProperties("cp")
public class BootifulProperties {
    
    private final String message;

    public BootifulProperties(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
