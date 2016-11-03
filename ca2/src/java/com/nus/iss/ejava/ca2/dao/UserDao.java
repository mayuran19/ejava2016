/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.dao;

import com.nus.iss.ejava.ca2.constant.AppConstant;
import com.nus.iss.ejava.ca2.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mugunthan
 */
@Stateless
public class UserDao {
    @PersistenceContext(unitName = AppConstant.PERSISTENT_UNIT_NAME) EntityManager em;
    
    public User create(User user){
        //need to return error if userId already exist
        em.persist(user);
        return user;
    }
    
    public Object find(String username){
        return em.find(User.class, username);
    }
}
