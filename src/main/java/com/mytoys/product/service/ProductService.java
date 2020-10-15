package com.mytoys.product.service;

import com.mytoys.product.entity.Product;
import com.mytoys.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public List<Product> listAllProducts() {
        log.info("Listing all products...");
        List<Product> products = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        log.info("products returned are " + products);
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        log.info("finding product with id " + id);
        Optional<Product> product = repository.findById(id);
        log.info("product found is " + product.orElse(null));
        return product;
    }

    public Iterable<Product> saveProducts(List<Product> products) {
        log.info("saving the list of products ");
        return repository.saveAll(products);
    }

    public Product saveProduct(Product product) {
        log.info("saving product " + product);
        return repository.save(product);
    }
}
