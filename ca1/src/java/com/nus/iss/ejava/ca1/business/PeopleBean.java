/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca1.business;

import com.nus.iss.ejava.ca1.constant.AppConstant;
import com.nus.iss.ejava.ca1.entity.People;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mayuran
 */
@Stateless
public class PeopleBean {
    @PersistenceContext(unitName = AppConstant.PERSISTENT_UNIT_NAME) EntityManager em;
    
    public People create(People people){
        
        em.persist(people);
        
        return people;
    }
    
    public People findByEmail(String email){
        System.out.println("email:" + email);
        People p = null;
        try{
            p = em.createNamedQuery("People.findByEmail", People.class).setParameter("email", email).getSingleResult();
        }catch(Exception e){
            System.out.println("No record found");
        }
        System.out.println("dddd:" + p);
        return p;
    }
}
