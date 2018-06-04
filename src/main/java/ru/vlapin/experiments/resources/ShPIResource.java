package ru.vlapin.experiments.resources;

import ru.vlapin.experiments.model.ShpiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("shpi")
@Produces(APPLICATION_JSON)
public class ShPIResource implements JsonRestfulWebResource {

    @GET
    public Response hello() {
        return ok(new ShpiResponse(true,
                new String[] {"19734223000797"}));
    }
}
