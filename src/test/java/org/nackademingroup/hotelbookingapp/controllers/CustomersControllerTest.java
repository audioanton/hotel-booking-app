package org.nackademingroup.hotelbookingapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomersControllerTest {
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    private String getBaseUrl() {
        String port = "8080";
        return "http://localhost:" + port + "/customers";
    }

    @Test
    void testListCustomerPageAccessible() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(getBaseUrl(), String.class);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
    }

    @Test
    void testCreateCustomerPageAccessible() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(getBaseUrl() + "/new", String.class);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
    }

    @Test
    void testInvalidCustomerPageIdFormat() {
        String fakeInvalidId = "abc";
        ResponseEntity<String> response = testRestTemplate.getForEntity(getBaseUrl() + "/" + fakeInvalidId, String.class);
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
    }

    @Test
    void testInvalidCustomerPageRedirect() {
        String fakeInvalidId = "3302";
        ResponseEntity<String> response = testRestTemplate.getForEntity(getBaseUrl() + "/" + fakeInvalidId, String.class);
        assert response.getBody().contains("<title>Customers</title>");
    }
}
