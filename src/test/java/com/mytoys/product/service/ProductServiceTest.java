package com.mytoys.product.service;

import com.mytoys.product.entity.Product;
import com.mytoys.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private List<Product> productList;

    @BeforeEach
    void setUp() {
        init();

        this.productList = new ArrayList<>();
        this.productList.add(new Product(1L, "TOY1", 10.0, 20.0, 10, "LEGO"));
        this.productList.add(new Product(2L, "TOY2", 15.0, 17.0, 2, "MY_TOYS"));
        this.productList.add(new Product(3L, "TOY3", 5.0, 7.5, 5, "ANY_BRAND"));
    }

    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProductsTest() {

        when(productRepository.findAll()).thenReturn(productList);
        List<Product> list = productService.listAllProducts();
        assertEquals(list.size(), productList.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(list.get(i), productList.get(i));
        }
    }

    @Test
    public void getProductByIdTest() {
        Product product = new Product(1L, "TOY1", 10.0, 20.0, 10, "LEGO");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> productById = productService.getProductById(1L);

        assertNotNull(productById.get());
        assertEquals(product.getName(), productById.get().getName());
        assertEquals(product.getBrand(), productById.get().getBrand());
        assertEquals(product.getStock(), productById.get().getStock());
        assertEquals(product.getOldPrice(), productById.get().getOldPrice());
        assertEquals(product.getPrice(), productById.get().getPrice());
    }


    @Test
    public void getAllProductsEmptyTest() {

        productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> returnedLis = productService.listAllProducts();
        assert(returnedLis.isEmpty());
        assertEquals(returnedLis.size(), productList.size());
    }

    @Test
    public void getProductByIdNotFoundTest() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> productById = productService.getProductById(1L);

        assertEquals(productById, Optional.empty());
    }
}