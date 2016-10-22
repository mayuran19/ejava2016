package com.nus.iss.ejava.ca1.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = "People.findByEmail", query = "select p from People p where p.email = :email")

@Entity(name = "People")
@Table(name = "people")
public class People implements Serializable {

    @Id
    private String pid;

    private String name;
    private String email;

    @OneToMany(mappedBy = "people")
    List<Appointment> appointments;
    
    private People(){
        
    }

    public People(String name, String email) {
        this.setPid(UUID.randomUUID().toString().substring(0, 8));
        this.name = name;
        this.email = email;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
