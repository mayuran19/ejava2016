/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author mayuran
 */
@Path("/")
public class EPodResource {

    @Path("/callback")
    @GET
    public Response receiveHQAck(@QueryParam("podId") String podId, @QueryParam("ack_id") String ackId) {
        if (podId == null || ackId == null){
            
        } else {            
            return Response.ok().build();
        }
    }
}
