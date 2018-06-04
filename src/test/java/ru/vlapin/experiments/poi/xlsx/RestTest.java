package ru.vlapin.experiments.poi.xlsx;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;

class RestTest {

    static String fooResourceUrl = "http://localhost:8080/webapi/hello";
    static RestTemplate restTemplate = new RestTemplate();

    @Test
    void name() {
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setHost(new InetSocketAddress(fooResourceUrl, 80));
        httpHeaders.setContentType(APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(APPLICATION_JSON));

        ResponseEntity<String> response = restTemplate
                .getForEntity(fooResourceUrl, String.class);
//                .postForEntity(fooResourceUrl, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(httpHeaders.getContentType()
                .includes(APPLICATION_JSON));

        assertThat(response.getBody(), is("\"Goodbye!\""));
    }
}
