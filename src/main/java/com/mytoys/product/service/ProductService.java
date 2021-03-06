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

    /**
     * A function that fetches all the products present in the in-memory database.
     *
     * @return List of {@link Product}
     */
    public List<Product> listAllProducts() {
        log.info("Listing all products...");
        List<Product> products = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        log.info("products returned are " + products);
        return products;
    }

    /**
     * Takes an id and tries to find a {@link Product} with that same id
     *
     * @param id
     * @return {@link Optional}<{@link Product}>
     */
    public Optional<Product> getProductById(Long id) {
        log.info("finding product with id " + id);
        Optional<Product> product = repository.findById(id);
        log.info("product found is " + product.orElse(null));
        return product;
    }

    /**
     * Stores a list of {@link Product} in the in-memory database
     *
     * @param products
     * @return an iterable of the stored list of {@link Product}.
     */
    public Iterable<Product> saveProducts(List<Product> products) {
        log.info("saving the list of products ");
        return repository.saveAll(products);
    }

    /**
     * Stores a {@link Product} in the in-memory database
     *
     * @param product
     * @return {@link Product}.
     */
    public Product saveProduct(Product product) {
        if (product != null)
            log.info("saving product with id " + product.getId());
        return repository.save(product);
    }

    /**
     * Stores a {@link Product} or edits it in the in-memory database
     *
     * @param product
     * @return {@link Product}.
     */
    public Product saveOrUpdateProduct(Product product) {
        if (null != product) {
            try {
                return getProductById(product.getId())
                        .map(existingProduct -> {
                            existingProduct.setName(product.getName());
                            existingProduct.setBrand(product.getBrand());
                            existingProduct.setPrice(product.getPrice());
                            existingProduct.setOldPrice(product.getOldPrice());
                            existingProduct.setStock(product.getStock());
                            log.info("found product and replacing it");
                            return saveProduct(existingProduct);
                        })
                        .orElseGet(() -> saveProduct(product));
            } catch (Throwable t) {
                log.info("An exception Occurred while saving the product with Id " + product.getId() + " " + t.getCause());
            }

        }
        return null;
    }
}
