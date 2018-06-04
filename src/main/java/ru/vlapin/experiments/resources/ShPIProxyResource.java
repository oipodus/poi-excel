package ru.vlapin.experiments.resources;

import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.vlapin.experiments.model.Shpi;
import ru.vlapin.experiments.model.ShpiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.InetSocketAddress;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static lombok.AccessLevel.PRIVATE;

@Path("shpiproxy")
@Produces(APPLICATION_JSON)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ShPIProxyResource implements JsonRestfulWebResource {

    String RESOURCE_URL = "http://localhost:8080/webapi/shpi";
    RestTemplate REST_TEMPLATE = new RestTemplate();

    @GET
    public Response get() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setHost(new InetSocketAddress("localhost", 8080));
        HttpEntity<Shpi> entity = new HttpEntity<>(Shpi.builder().build(), headers);

        ResponseEntity<String> response = REST_TEMPLATE.postForEntity(RESOURCE_URL, entity, String.class);

        ShpiResponse shPIResponse = fromJSON(response.getBody(), ShpiResponse.class);

        return ok(shPIResponse.getBarcodes()[0]);

    }
}
