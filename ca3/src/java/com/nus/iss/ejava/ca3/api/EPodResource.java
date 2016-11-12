/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.api;

import javax.ws.rs.QueryParam;
import com.nus.iss.ejava.ca3.business.DeliveryBusiness;
import com.nus.iss.ejava.ca3.business.PodBusiness;
import com.nus.iss.ejava.ca3.entity.Delivery;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.nus.iss.ejava.ca3.entity.Pod;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author mayuran
 */
@Path("/")
public class EPodResource {

    @EJB
    private DeliveryBusiness deliveryBusiness;

    @EJB
    private PodBusiness podBusiness;

    @GET
    @Path("api/item")
    public Response getAllDeliveries() {
        List<Delivery> list = deliveryBusiness.getAll();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        list.stream().map(d -> {
            return Json.createObjectBuilder()
                    .add("teamId", d.getPod().getPodId())
                    .add("podId", "")
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
    public Response receiveHQAck(@QueryParam("podId") String podId, @QueryParam("ackId") String ackId) {
        if (podId == null || ackId == null) {
            return Response.ok(Response.Status.BAD_REQUEST).build();
        } else {
            Pod pod = podBusiness.find(Integer.parseInt(podId));
            if (pod != null) {
                pod.setAckId(ackId);
                podBusiness.update(pod);
                return Response.ok().build();
            } else {
                return Response.ok(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("podId") String podId, @FormDataParam("note") String note, @FormDataParam("image") InputStream image, @FormDataParam("time") Long time) throws IOException{
        System.out.println("podId:" + podId);
        System.out.println("file" + image);
        Pod pod = podBusiness.find(Integer.valueOf(podId));
        Date date = new Date();
        date.setTime(time);
        pod.setDeliveryDate(date);
        pod.setImage(readFully(image));
        pod.setNote(note);
        podBusiness.update(pod);
        
        return Response.ok().build();
    }

    public static byte[] readFully(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }
}
