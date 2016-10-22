package com.nus.iss.ejava.ca1.api;

import com.nus.iss.ejava.ca1.business.PeopleBean;
import com.nus.iss.ejava.ca1.entity.People;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mugunthan
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    @EJB
    PeopleBean peopleBean;

    /**
     *
     * @param email
     * @return
     */
    @GET
    public Response findByEmail(@QueryParam("email") String email) {
        People people = peopleBean.findByEmail(email);
        if (null != people) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("email", people.getEmail())
                    .add("name", people.getName())
                    .build();
            return Response.ok().entity(jsonObject).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addPeople(@FormParam("email") String email, @FormParam("name") String name) {
        People existingPeople = peopleBean.findByEmail(email);
        if (null == existingPeople) {
            People people = peopleBean.create(new People(name, email));
            return Response.ok(people).build();
        }else{
            JsonObject jsonObject = Json.createObjectBuilder().add("error", "Email already exists").build();
            return Response.status(Response.Status.CONFLICT).entity(jsonObject.toString()).build();
        }
    }
}
