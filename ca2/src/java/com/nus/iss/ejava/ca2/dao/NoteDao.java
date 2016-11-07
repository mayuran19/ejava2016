/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.dao;

import com.nus.iss.ejava.ca2.constant.AppConstant;
import com.nus.iss.ejava.ca2.entity.Note;
import com.nus.iss.ejava.ca2.notification.NoteNotifier;
import java.util.List;
import java.util.Observable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.nus.iss.ejava.ca2.constant.Category;

/**
 *
 * @author Mugunthan
 */
@Stateless
public class NoteDao extends Observable {

    @PersistenceContext(unitName = AppConstant.PERSISTENT_UNIT_NAME)
    EntityManager em;

    public Note create(Note note) {
        //need to return error if userId already exist
        em.persist(note);
        System.out.println("Notifying the observers");
        em.flush();
        NoteNotifier.getInstance().notify(note);
        return note;
    }

    public List<Note> findByUserid(String userid) {
        return em.createNamedQuery("Note.findByUserid", Note.class).setParameter("userid", userid).getResultList();
    }

    public List<Note> findByCategory(String category) {
        if (category.equals("ALL")) {
            return em.createNamedQuery("Note.findAll", Note.class).getResultList();
        } else {
            Category c = null;
            if(category.equals("SOCIAL")){
                c = Category.SOCIAL;
            }else if(category.equals("FOR_SALE")){
                c = Category.FOR_SALE;
            }else if(category.equals("JOBS")){
                c = Category.JOBS;
            }else if(category.equals("TUTION")){
                c = Category.TUTION;
            }
            return em.createNamedQuery("Note.findByCategory", Note.class).setParameter("category", c).getResultList();
        }
    }
}
