/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.business;

import java.util.List;
import javax.ejb.Stateless;
import com.nus.iss.ejava.ca3.entity.Delivery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mayuran
 */
@Stateless
public class DeliveryBusiness {
    @PersistenceContext(unitName = "deliveryPU") EntityManager em;
    public List<Delivery> getAll(){
        return em.createNamedQuery("Delivery.findAll", Delivery.class).getResultList();
    }
    
    public Delivery create(Delivery delivery)
    {
        em.persist(delivery);
        return delivery;
    }
}
