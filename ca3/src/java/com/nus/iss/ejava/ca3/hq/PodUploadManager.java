/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.business.PodBusiness;
import com.nus.iss.ejava.ca3.constant.AppConstant;
import com.nus.iss.ejava.ca3.entity.Delivery;
import com.nus.iss.ejava.ca3.entity.Pod;
import javax.ejb.EJB;

/**
 *
 * @author Mugunthan
 */
public class PodUploadManager {

    @EJB
    PodBusiness podBusiness;

    public void podUpload(Pod pod) {
        try {
            while (receivedAcknowledge(pod.getPodId())) {
                Thread t = new Thread(new PodUpload(pod));
                t.start();
                Thread.sleep(AppConstant.HQ_ACK_WAIT_PERIOD);//Wait for ack from HQ
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean receivedAcknowledge(Integer podId) {
        Pod pod = podBusiness.find(podId);
        boolean acknowledged = false;
        if (pod != null && pod.getAckId() != null) {
            acknowledged = true;
        }

        return acknowledged;
    }

    // This method implemented for development pupose only. Need to be removed
//    public static void main(String[] args) {
//        Pod pod = new Pod();
//        pod.setPodId(1);
//        pod.setDelivery(new Delivery("dilivery1", "add", "123456"));
//        pod.setImage(new byte[10]);
//        pod.setNote("Some info");
//        PodUploadManager manager = new PodUploadManager();
//        manager.podUpload(pod);
//    }
}
