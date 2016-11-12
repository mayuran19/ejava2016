/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.business.PodBusiness;
import com.nus.iss.ejava.ca3.entity.Pod;

/**
 *
 * @author Mugunthan
 */
public class PodUploadManager {

    PodBusiness podBusiness;

    public void podUpload(Pod pod) {
        PodUpload d = new PodUpload(pod);
        d.setPodBusiness(podBusiness);
        Thread t = new Thread(d);
        t.start();
    }

    public PodBusiness getPodBusiness() {
        return podBusiness;
    }

    public void setPodBusiness(PodBusiness podBusiness) {
        this.podBusiness = podBusiness;
    }
}
