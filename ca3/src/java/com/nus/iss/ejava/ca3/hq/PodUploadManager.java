/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.constant.AppConstant;
import com.nus.iss.ejava.ca3.entity.Pod;

/**
 *
 * @author Mugunthan
 */
public class PodUploadManager {

    public void podUpload(Pod pod) {
        try {
            while (receivedAcknowledge(pod.getPodId())) {
                Thread t = new Thread(new PodUpload(pod));
                t.start();
                Thread.sleep(AppConstant.HQ_ACK_WAIT_PERIOD);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Pod pod = new Pod();
        PodUploadManager manager = new PodUploadManager();
        manager.podUpload(pod);
    }

    private boolean receivedAcknowledge(Integer podId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
