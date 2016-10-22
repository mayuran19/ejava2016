package com.nus.iss.ejava.ca1.api;

import com.nus.iss.ejava.ca1.business.AppointmentBean;
import com.nus.iss.ejava.ca1.business.PeopleBean;
import com.nus.iss.ejava.ca1.entity.Appointment;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        appointments.stream().map((a) -> {
            return Json.createObjectBuilder()
                    .add("dateTime", sdf.format(a.getApptDate()))
                    .add("description", a.getDescription())
                    .build();
        }).forEach(json -> arrayBuilder.add(json));
        JsonArray array = arrayBuilder.build();
        return Response.ok(array).build();
    }

}
