package com.mytoys.product.testing.integration;

import com.mytoys.product.ProductServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceSingleProductIntegrationTesting {
    final String expected = "{\"id\":33,\"name\":\"LEGO #657\",\"price\":19.99,\"oldPrice\":24.99,\"stock\":324,\"brand\":\"LEGO\"}";
    final String uri = "/product/33";
    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveProduct() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        log.info("making a Rest Call to fetch one product");
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, String.class);


        log.info("Asserting either the product data is returned correctly or a Not found String is returned ");
        assertThat(response.getBody().toString(), anyOf(is(expected.toString()), is("Could not find product with id = 33")));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}







