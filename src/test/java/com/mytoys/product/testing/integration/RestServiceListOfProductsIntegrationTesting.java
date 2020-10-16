package com.mytoys.product.testing.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytoys.product.config.CSVConfig;
import com.mytoys.product.entity.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class RestServiceListOfProductsIntegrationTesting {

    public static final String HTTP_SERVICE_URL = "http://localhost:8080/product";

    @Autowired
    CSVConfig csvConfig;

    @Test
    public void
    AssertProductInformationIsRetrievedCorrectly() throws ClientProtocolException, IOException {

        HttpUriRequest request = new HttpGet(HTTP_SERVICE_URL);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        List<Product> products = getProductsFromResponse(response);
        Assert.assertNotNull(products);
    }

    private List<Product> getProductsFromResponse(HttpResponse response) throws IOException {
        final String jsonFromResponse = EntityUtils.toString(response.getEntity());
        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, new TypeReference<>() {});
    }
}
