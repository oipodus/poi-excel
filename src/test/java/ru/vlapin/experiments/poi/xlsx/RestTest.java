package ru.vlapin.experiments.poi.xlsx;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestTest {

    String fooResourceUrl = "http://localhost:8080/webapi/hello";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void name() {

        HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
        ResponseEntity<String> response = restTemplate
                .getForEntity(fooResourceUrl, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(httpHeaders.getContentType()
                .includes(MediaType.APPLICATION_JSON));

        assertThat(response.getBody(), is("\"Goodbye!\""));
    }
}
