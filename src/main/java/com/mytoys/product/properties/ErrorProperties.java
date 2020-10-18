package com.mytoys.product.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "response.error.message")
@Getter
@Setter
public class ErrorProperties {
    private String productNotFound;
    private String pathNotFound;
}
