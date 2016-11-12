/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.constant.AppConstant;
import com.nus.iss.ejava.ca3.entity.Pod;
import java.io.File;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 *
 * @author Mugunthan
 */
public class PodUpload implements Runnable {

    private Pod pod;

    public PodUpload(Pod p) {
        this.pod = p;
    }

    @Override
    public void run() {
        System.out.println("Pod upload start..");
        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
        WebTarget target = client.target(AppConstant.HQ_POD_UPLOAD_URL);

        MultiPart part = new MultiPart();

//        FileDataBodyPart imgPart = new FileDataBodyPart("image",
//                new File("/home/cmlee/Pictures/ca3.png"),
//                MediaType.APPLICATION_OCTET_STREAM_TYPE);
//        imgPart.setContentDisposition(
//                FormDataContentDisposition.name("image")
//                .fileName(pod.getPodId() + ".png").build());
//        Bodypart imgPart = pod.getImage().

        MultiPart formData = new FormDataMultiPart()
                .field("teamId", AppConstant.TEAM_ID, MediaType.TEXT_PLAIN_TYPE)
                .field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                .field("callback", AppConstant.POD_ACK_CALLBACK_URL, MediaType.TEXT_PLAIN_TYPE)
                .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
//                .field("time", Long.toString(System.currentTimeMillis()), MediaType.TEXT_PLAIN_TYPE)
                .field("image", pod.getImage(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
//                .bodyPart(imgPart);
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

        Invocation.Builder inv = target.request();

        System.out.println("FormData...: " + formData);
        System.out.println("teamId...: " + AppConstant.TEAM_ID);
        System.out.println("podId...: " + pod.getPodId());
        System.out.println("callback...: " + AppConstant.POD_ACK_CALLBACK_URL);
        System.out.println("note...: " + pod.getNote());
        System.out.println("image...: " + pod.getImage());

        Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Call Response...:" + callResp.getStatus());
        System.out.println("Call StatusInfo...:" + callResp.getStatusInfo());
        System.out.println("Pod upload End..");
    }

}
