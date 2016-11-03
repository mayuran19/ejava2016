/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.web;

import com.nus.iss.ejava.ca2.constant.Category;
import com.nus.iss.ejava.ca2.dao.NoteDao;
import com.nus.iss.ejava.ca2.dao.UserDao;
import com.nus.iss.ejava.ca2.entity.Note;
import com.nus.iss.ejava.ca2.entity.User;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Mugunthan
 */
@ViewScoped
@Named("noteBean")
public class NoteBean implements Serializable {

    @EJB UserDao userDao;
    @EJB NoteDao noteDao;
    
    private Category[] catValues;
    private String title;
    private String content;
    private String category;

    public Category[] getCatValues() {
        catValues =  Category.values();
        return catValues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String addNote(){
        
        User user = (User) userDao.find("abc"); //Need to change with logged User
        
        Note note = new Note(title, user, new Date(), Category.valueOf(category), content);
        noteDao.create(note);
        return "/login.xhtml";
    }
}
