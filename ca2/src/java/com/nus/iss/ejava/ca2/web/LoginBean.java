package com.nus.iss.ejava.ca2.web;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ViewScoped
@Named
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

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

    public String login() {
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        try {
            System.out.println("username:" + username);
            System.out.println("password:" + password);
            req.login(username, password);
        } catch (Throwable t) {
            t.printStackTrace();
            FacesContext.getCurrentInstance()
                    .addMessage("loginform", new FacesMessage("Incorrect username/password"));
            return (null);
        }

        return ("secure/notes/list?faces-redirect=true");
    }
    
    public String logout(){
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return ("/login?faces-redirect=true");
    }

}
