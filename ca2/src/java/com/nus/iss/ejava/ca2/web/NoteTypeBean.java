/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mayuran
 */
@Named("noteTypeBean")
@RequestScoped
public class NoteTypeBean {
    private String noteType;

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
    
    public String displayNotes(){
        return ("/notes?category=" + noteType + "&faces-redirect=true");
    }
}
