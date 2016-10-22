package com.nus.iss.ejava.ca1.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

@Entity
public class Appointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Integer appt_id;
    
    private String description;
    private Date appt_date;
    
    @EmbeddedId
    private Integer pid;

    public Integer getAppt_id() {
        return appt_id;
    }

    public void setAppt_id(Integer appt_id) {
        this.appt_id = appt_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAppt_date() {
        return appt_date;
    }

    public void setAppt_date(Date appt_date) {
        this.appt_date = appt_date;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    
}
