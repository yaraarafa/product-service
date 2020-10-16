package com.mytoys.product.testing.integration;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ResponseTypeTest {

    public static final String HTTP_SERVICE_URL = "http://localhost:8080/product";

    @Test
    public void
    AssertRequestReturnsJSONData()
            throws ClientProtocolException, IOException {

        log.info("preparing the rest call in AssertRequestReturnsJSONData");
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet(HTTP_SERVICE_URL);

        log.info("make the rest call");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        log.info("make sure response is returned as JSON");
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }
}
