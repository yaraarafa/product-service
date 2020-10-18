package com.mytoys.product.testing.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytoys.product.ProductServiceApplication;
import com.mytoys.product.TestUtils;
import com.mytoys.product.properties.TestProperties;
import com.mytoys.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceListOfProductsIntegrationTesting {

    @Autowired
    private TestProperties properties;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void AssertProductInformationIsRetrievedCorrectly() throws IOException {

        log.info("Making a rest call to test the Retrieve all products function");
        String url = TestUtils.createURLWithPort(properties.getServer(), port, properties.getAllProducts());
        ResponseEntity<String> response = TestUtils.executeGetRequest(headers, restTemplate, url);
        List<Product> products = getProductsFromResponse(response);
        log.info("Asserting the List is not null");
        Assert.assertNotNull(products);
    }

    private List<Product> getProductsFromResponse(ResponseEntity<String> response) throws IOException {
        log.info("Extracting the List of products from the response object using Jackson");
        final String jsonFromResponse = response.getBody();
        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, new TypeReference<>() {
        });
    }


}
