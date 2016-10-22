package com.nus.iss.ejava.ca1.api;

import com.nus.iss.ejava.ca1.business.PeopleBean;
import com.nus.iss.ejava.ca1.entity.People;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    @EJB PeopleBean peopleBean;
    
    /**
     *
     * @param email
     * @return
     */
    @GET
    public Response findByEmail(@QueryParam("email") String email) {
        List<People> peoples = peopleBean.findByEmail(email);
        
        System.out.println("people:" + peoples.size());
        return Response.ok(peoples).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addPeople(@FormParam("email") String email, @FormParam("name") String name) {
            
        People people = peopleBean.create(new People(email, name));
        return Response.ok(people).build();
    }
}
