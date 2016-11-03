/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.web;

import com.nus.iss.ejava.ca2.constant.AppConstant;
import com.nus.iss.ejava.ca2.dao.UserDao;
import com.nus.iss.ejava.ca2.entity.User;
import com.nus.iss.ejava.ca2.util.HashPasswordGenerator;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Mugunthan
 */
@ViewScoped
@Named
public class RegisterBean implements Serializable {

    @EJB
    UserDao userDao;

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String retypePassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String addUser() {
        if (password.equals(retypePassword)) {
            Object ex = userDao.find(username);
            if (ex == null) {
                String hashedPassword = HashPasswordGenerator.generateHashPassword(password);
                User user = new User(username, hashedPassword, AppConstant.GROUP_NAME);
                userDao.create(user);
                return ("login.xhtml");
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage("registerform", new FacesMessage("Username already exit"));
                return (null);
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("registerform", new FacesMessage("Password does not match"));
            return (null);

        }
    }

}
