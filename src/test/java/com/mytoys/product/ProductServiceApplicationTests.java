package com.mytoys.product;

import com.mytoys.product.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private ProductController controller;

    @Test
    public void autowiring() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void contextLoads() {
    }
}
