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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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
        FacesContext fc = FacesContext.getCurrentInstance(); 
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        User user = (User) userDao.find(req.getRemoteUser());
        
        Note note = new Note(title, user, new Date(), Category.valueOf(category), content);
        noteDao.create(note);
        return "/secure/notes/list?faces-redirect=true";
    }
    
    public List<Note> getNotes(){
        FacesContext fc = FacesContext.getCurrentInstance(); 
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        User user = (User) userDao.find(req.getRemoteUser());
        
        List<Note> result = noteDao.findByUserid(user.getUserId());
        
        return result;
    }
    
    public List<Note> getNotesByCategory(){
        FacesContext fc = FacesContext.getCurrentInstance(); 
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        this.category = req.getParameter("category");
        List<Note> results = noteDao.findByCategory(category);
        
        return results;
    }
    
    public String getSocketURL(){
        FacesContext fc = FacesContext.getCurrentInstance(); 
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String server = request.getServerName();
        Integer port = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = "ws://" + server + ":" + port.toString() + "" + contextPath + "/notes?category=" + request.getParameter("category");
        return url;
    }
    
    public String getTitleString(){
        FacesContext fc = FacesContext.getCurrentInstance(); 
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        this.category = req.getParameter("category");
        return "SHOWING " + req.getParameter("category").toUpperCase();
    }
}
