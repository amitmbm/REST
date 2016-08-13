package com.ami.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by amit.khandelwal on 03/08/16.
 */

@Path("/status")
public class HeathCheckController {

    @GET
    public Response getHeathCheck() {
        System.out.println("inside health check controller");
        return Response.status(200).entity("checking jenkins").build();
    }

}


