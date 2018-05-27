package ru.vlapin.experiments.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.vlapin.experiments.model.ShPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("shpiproxy")
@Produces(APPLICATION_JSON)
public class ShPIProxyResource implements JsonRestfulWebResource {

    String resourceUrl = "http://localhost:8080/webapi/shpi";
    RestTemplate restTemplate = new RestTemplate();

    @GET
    public Response get() {
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(resourceUrl);
        ResponseEntity<String> response = restTemplate
                .getForEntity(resourceUrl, String.class);

        ShPI shPI = fromJSON(response.getBody(), ShPI.class);

        return ok(shPI.getBarcodes()[0]);

    }
}
