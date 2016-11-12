/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.api;

<<<<<<< HEAD
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
=======
import com.nus.iss.ejava.ca3.business.DeliveryBusiness;
import com.nus.iss.ejava.ca3.entity.Delivery;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author mayuran
 */


@Path("/")
public class EPodResource {

    @Inject
    private DeliveryBusiness deliveryBusiness;

    @GET
    @Path("api/item")
    public Response getAllDeliveries() {
        List<Delivery> list = deliveryBusiness.getAll();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        list.stream().map(d -> {
            return Json.createObjectBuilder()
                    .add("teamId", "")
                    .add("podId", d.getPod().getPodId())
                    .add("name", d.getName())
                    .add("address", d.getAddress())
                    .add("phone", d.getPhone())
                    .build();
        }).forEach(json -> {
            arrayBuilder.add(json);
        });
        JsonArray array = arrayBuilder.build();
        return Response.ok(array).build();
    }

    @Path("/callback")
    @GET
    public Response receiveHQAck(@QueryParam("podId") String podId, @QueryParam("ack_id") String ackId) {
        if (podId == null || ackId == null) {
            return Response.ok(Response.Status.CONFLICT).build();
        } else {
            //todo
            return Response.ok().build();
        }
    }
}
