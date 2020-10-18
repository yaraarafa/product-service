package com.mytoys.product;

import com.mytoys.product.controller.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ProductServiceApplicationTests {
    @Autowired
    private ProductController controller;

    @Test
    public void autoWiring() throws Exception {
        log.info("Asserting The Controller  is auto-wired with no problems");
        assertThat(controller).isNotNull();
    }

    @Test
    void contextLoads() {
    }
}
