package com.mytoys.product.testing.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytoys.product.config.CSVConfig;
import com.mytoys.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@Slf4j
public class RestServiceListOfProductsIntegrationTesting {

    public static final String HTTP_SERVICE_URL = "http://localhost:8080/product";

    @Autowired
    CSVConfig csvConfig;

    @Test
    public void
    AssertProductInformationIsRetrievedCorrectly() throws ClientProtocolException, IOException {

        log.info("Making a rest call to test the Retrieve all products function");
        HttpUriRequest request = new HttpGet(HTTP_SERVICE_URL);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        List<Product> products = getProductsFromResponse(response);
        log.info("Asserting the List is not null");
        Assert.assertNotNull(products);
    }

    private List<Product> getProductsFromResponse(HttpResponse response) throws IOException {
        log.info("Extracting the List of products from the response object using Jackson");
        final String jsonFromResponse = EntityUtils.toString(response.getEntity());
        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, new TypeReference<>() {
        });
    }
}
