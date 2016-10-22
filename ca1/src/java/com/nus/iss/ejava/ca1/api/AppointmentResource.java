package com.nus.iss.ejava.ca1.api;

import com.nus.iss.ejava.ca1.business.AppointmentBean;
import com.nus.iss.ejava.ca1.business.PeopleBean;
import com.nus.iss.ejava.ca1.entity.Appointment;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    @EJB AppointmentBean appointmentBean;

//    @GET
//    @Path("/{id}")
//    public Response getAppointment() {
//
//        return Response.ok().build();
//    }
//    
    @GET
    @Path("/{peopleEmail}")
    public Response getAllAppointmentsByPeople(@PathParam("peopleEmail") String email) {

        List<Appointment> appointments = appointmentBean.findByEmail(email);
        return Response.ok(appointments).build();
    }

}
