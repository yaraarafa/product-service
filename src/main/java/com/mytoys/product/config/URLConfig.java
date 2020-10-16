package com.mytoys.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "test.local.service.url")
@Getter
@Setter
public class URLConfig {
    private String path;
    private String param;
}
