package com.mytoys.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "csv.file.load")
@Getter
@Setter
public class CSVConfig {
    private String fileName;
    private String path;
}
