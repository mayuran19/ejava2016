/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.web;

import com.nus.iss.ejava.ca3.business.DeliveryBusiness;
import com.nus.iss.ejava.ca3.business.PodBusiness;
import com.nus.iss.ejava.ca3.entity.Delivery;
import com.nus.iss.ejava.ca3.entity.Pod;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sdwilliam
 */
@Named
@ViewScoped
public class DeliveryBean implements Serializable{
    
    @EJB DeliveryBusiness deliveryBusiness;
    @EJB PodBusiness podBusiness;
    
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String submit()
    {
        System.out.println("name: "+ name);
        System.out.println("address: " + address);
        System.out.println("phone: " + phone);
        
        Delivery delivery = new Delivery(name, address, phone);
        Pod pod = new Pod(delivery);
        podBusiness.create(pod);
        
        return null;
    }
    
    public List<Delivery> getDeliveries(){
        System.out.println("ddddddd");
        List<Delivery> deliveries = deliveryBusiness.getAll();
        
        return deliveries;
    }
    
}
