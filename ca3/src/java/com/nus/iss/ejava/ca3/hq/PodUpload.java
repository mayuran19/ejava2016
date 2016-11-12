/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.business.PodBusiness;
import com.nus.iss.ejava.ca3.constant.AppConstant;
import com.nus.iss.ejava.ca3.entity.Pod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author Mugunthan
 */
public class PodUpload implements Runnable {

    PodBusiness podBusiness;
    private Pod pod;

    public PodUpload(Pod p) {
        this.pod = p;
    }

    @Override
    public void run() {
        try {
            System.out.println("Inside thread");
            while (!receivedAcknowledge(pod.getPodId())) {
                System.out.println("Pod upload start..");
                Client client = ClientBuilder.newBuilder()
                        .register(MultiPartFeature.class)
                        .build();
                WebTarget target = client.target(AppConstant.HQ_POD_UPLOAD_URL);

                MultiPart formData = new FormDataMultiPart()
                        .field("teamId", AppConstant.TEAM_ID, MediaType.TEXT_PLAIN_TYPE)
                        .field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                        .field("callback", AppConstant.POD_ACK_CALLBACK_URL, MediaType.TEXT_PLAIN_TYPE)
                        .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
                        .field("image", pod.getImage(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

                Invocation.Builder inv = target.request();

                Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

                System.out.println("Call Response...:" + callResp.getStatus());
                System.out.println("Call StatusInfo...:" + callResp.getStatusInfo());
                Thread.sleep(AppConstant.HQ_ACK_WAIT_PERIOD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean receivedAcknowledge(Integer podId) {
        Pod pod = podBusiness.find(podId);
        boolean acknowledged = false;
        System.out.println("Ack id:" + pod.getAckId());
        if (pod != null && pod.getAckId() != null) {
            acknowledged = true;
        }
        System.out.println("return: " + acknowledged);
        return acknowledged;
    }

    public PodBusiness getPodBusiness() {
        return podBusiness;
    }

    public void setPodBusiness(PodBusiness podBusiness) {
        this.podBusiness = podBusiness;
    }

    public Pod getPod() {
        return pod;
    }

    public void setPod(Pod pod) {
        this.pod = pod;
    }

}
