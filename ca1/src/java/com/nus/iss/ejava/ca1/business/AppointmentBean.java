/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca1.business;

import com.nus.iss.ejava.ca1.constant.AppConstant;
import com.nus.iss.ejava.ca1.entity.Appointment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mayuran
 */
@Stateless
public class AppointmentBean {
    @PersistenceContext(unitName = AppConstant.PERSISTENT_UNIT_NAME) EntityManager em;
    
    public List<Appointment> findByEmail(String email){
        return em.createNamedQuery("Appointment.findByEmail", Appointment.class).setParameter("email", email).getResultList();
    }
}
