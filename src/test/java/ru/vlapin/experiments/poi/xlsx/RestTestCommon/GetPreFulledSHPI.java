package ru.vlapin.experiments.poi.xlsx.RestTestCommon;

import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.vlapin.experiments.model.ClientData;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@FieldDefaults(level = PRIVATE)
public class GetPreFulledSHPI {
    @BeforeAll
    static void setUp() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
    }

    String fooResourceUrl = "http://tracking-v3.test.russianpost.ru/barcode-fetch/v1/barcodes/fetch";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void name() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        //   httpHeaders.setHost(new InetSocketAddress("tracking-v3.test.russianpost.ru",80));
        ResponseEntity<String> response = restTemplate
                .postForEntity(fooResourceUrl, new HttpEntity<>(ClientData.builder()
                        .build(), httpHeaders), String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(httpHeaders.getContentType()
                .includes(APPLICATION_JSON));

        //   assertThat(response.getBody(), is("\"Goodbye!\""));
    }
}
