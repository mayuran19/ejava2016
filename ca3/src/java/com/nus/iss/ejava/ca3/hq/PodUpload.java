/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.hq;

import com.nus.iss.ejava.ca3.entity.Pod;

/**
 *
 * @author Mugunthan
 */
public class PodUpload implements Runnable {
    
    private Pod pod;
    
    public PodUpload(Pod p){
        this.pod = p;
    }

    @Override
    public void run() {
        System.out.println("Thread Started");
    }

}
