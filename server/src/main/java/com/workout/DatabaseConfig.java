package com.workout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;

    public String getUrl() {
        return url;
    }
}
