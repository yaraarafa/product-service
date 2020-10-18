package com.mytoys.product.controller;

import com.mytoys.product.entity.Product;
import com.mytoys.product.exception.ProductNotFoundException;
import com.mytoys.product.config.ErrorConfig;
import com.mytoys.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private ErrorConfig properties;

    private ProductService productService;

    /**
     * List all products currently loaded from a CSV file using HTTP Get in JSON format
     *
     * @return List  of {@link Product}
     */
    @GetMapping("/product")
    List<Product> getAllProducts() {
        return productService.listAllProducts();
    }

    /**
     * Returns the product with the given Id currently loaded from a CSV file using HTTP Get in JSON format
     *
     * @param id
     * @return {@link Product}
     */
    @GetMapping("/product/{id}")
    Product findProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(properties.getProductNotFound(), id));
    }


}
