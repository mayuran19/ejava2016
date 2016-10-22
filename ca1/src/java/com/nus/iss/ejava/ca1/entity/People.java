package com.nus.iss.ejava.ca1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class People {
    
    @Id
    private Integer pid;
    
    private String name;
    private String email;
    
    public People() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
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
