package com.mytoys.product.testing.integration;

import com.mytoys.product.ProductServiceApplication;
import com.mytoys.product.TestUtils;
import com.mytoys.product.properties.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResponseTypeTest {

    @Autowired
    private TestProperties properties;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();


    @Test
    public void
    AssertRequestReturnsJSONData() {

        log.info("Making the rest call in AssertRequestReturnsJSONData");
        String jsonMimeType = MediaType.APPLICATION_JSON_VALUE;
        String url = TestUtils.createURLWithPort(properties.getServer(), port, properties.getAllProducts());
        ResponseEntity<String> response = TestUtils.executeGetRequest(headers, restTemplate, url);

        log.info("make sure response is returned as JSON");
        MediaType mimeType = response.getHeaders().getContentType();
        assertEquals(jsonMimeType, mimeType.toString());
    }
}
