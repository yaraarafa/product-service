package com.mytoys.product;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class TestUtils {
    public static String createURLWithPort(String server, int port, String uri) {
        return server + port + uri;
    }

    public static ResponseEntity<String> executeGetRequest(HttpHeaders headers, TestRestTemplate restTemplate, String url) {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET, entity, String.class);
    }
}
