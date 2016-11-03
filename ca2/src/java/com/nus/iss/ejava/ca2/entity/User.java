/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author Mugunthan
 */
@Entity(name = "User")
@Table(name = "users")
@SecondaryTable(name = "groups", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userid"))
public class User implements Serializable {

    @Id
    private String userId;

    private String password;

    @Column(name = "groupid", table = "groups")
    private String group;

    @OneToMany(mappedBy = "user")
    List<Note> notes;

    public User() {
    }

    public User(String userId, String password, String group) {
        this.userId = userId;
        this.password = password;
        this.group = group;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}
