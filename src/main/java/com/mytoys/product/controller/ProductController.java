package com.mytoys.product.controller;

import com.mytoys.product.entity.Product;
import com.mytoys.product.exception.ProductNotFoundException;
import com.mytoys.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    List<Product> getAllProducts() {
        return productService.listAllProducts();
    }


    // Single product retrieval
    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {

        return productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


}
