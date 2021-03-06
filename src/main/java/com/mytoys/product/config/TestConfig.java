package com.mytoys.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TestConfig {

    @Value("${test.endpoint.products}")
    private String allProducts;
    @Value("${test.endpoint.product}")
    private String oneProduct;
    @Value("${test.endpoint.expected.result}")
    private String expectedProduct;
    @Value("${test.endpoint.server}")
    private String server;


}
