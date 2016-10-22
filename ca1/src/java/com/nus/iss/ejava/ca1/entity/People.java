
package com.nus.iss.ejava.ca1.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQuery(name = "People.findByEmail", query = "select p from People p where p.email = :email")

@Entity(name = "People")
public class People implements Serializable {
    @Id
    private Integer pid;
    
    private String name;
    private String email;
    
    @OneToMany(mappedBy= "people")
    List<Appointment> appointments;

    public People(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
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
