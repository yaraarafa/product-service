package com.mytoys.product.controller;

import com.mytoys.product.entity.Product;
import com.mytoys.product.config.ErrorConfig;
import com.mytoys.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ErrorConfig errorConfig;
    @MockBean
    private ProductService productService;

    private List<Product> productList;


    @BeforeEach
    void setUp() {
        this.productList = new ArrayList<>();
        this.productList.add(new Product(1L, "TOY1", 10.0, 20.0, 10, "LEGO"));
        this.productList.add(new Product(2L, "TOY2", 15.0, 17.0, 2, "MY_TOYS"));
        this.productList.add(new Product(3L, "TOY3", 5.0, 7.5, 5, "ANY_BRAND"));

    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.listAllProducts()).thenReturn(productList);
        this.mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productList.size())));
    }

    @Test
    void findProductById() throws Exception {
        final Long productId = 1L;
        final Product product = new Product(1L, "TOY1", 10.0, 20.0, 10, "LEGO");

        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        this.mockMvc.perform(get("/product/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.oldPrice", is(product.getOldPrice())))
                .andExpect(jsonPath("$.brand", is(product.getBrand())))
                .andExpect(jsonPath("$.stock", is(product.getStock())));
    }

    @Test
    void shouldReturn404WhenCannotFindProductById() throws Exception {
        final Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/product/{id}", productId))
                .andExpect(status().isNotFound());
    }
}