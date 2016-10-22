package com.nus.iss.ejava.ca1.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "Appointment.findByEmail", query = "select p.appointments from People p where p.email = :email")

@Entity(name = "Appointment")
@Table(name = "appointment")
public class Appointment implements Serializable {

    @Id
    @Column(name = "appt_id")
    @GeneratedValue(strategy = IDENTITY)
    private Integer apptId;

    private String description;

    @Column(name = "appt_date")
    private Date apptDate;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private People people;

    public Integer getApptId() {
        return apptId;
    }

    public void setApptId(Integer apptId) {
        this.apptId = apptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

}
