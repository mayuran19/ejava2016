package com.nus.iss.ejava.ca1.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mugunthan
 */
@Path("/appointment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {

    @GET
    @Path("/{id}")
    public Response getAppointment() {

        return Response.ok().build();
    }
//    
//    @GET
//    @Path("/{name}")
//    public Response getAllAppointmentsForPerson() {
//
//        return Response.ok().build();
//    }

}
