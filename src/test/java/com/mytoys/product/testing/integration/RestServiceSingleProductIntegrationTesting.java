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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceSingleProductIntegrationTesting {

    @Autowired
    private TestProperties properties;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveProduct() {

        log.info("making a Rest Call to fetch one product");
        String url = TestUtils.createURLWithPort(properties.getServer(), port, properties.getOneProduct());
        ResponseEntity<String> response = TestUtils.executeGetRequest(headers, restTemplate, url);


        log.info("Asserting either the product data is returned correctly or a Not found String is returned ");
        assertThat(response.getBody().toString(), anyOf(is(properties.getExpectedProduct()),
                is("Could not find product with id = 33")));
    }


}







