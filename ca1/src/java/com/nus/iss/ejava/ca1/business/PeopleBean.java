/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca1.business;

import com.nus.iss.ejava.ca1.entity.People;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author mayuran
 */
@Stateless
public class PeopleBean {
    @Resource(lookup = "jdbc/appointment")
    private DataSource dataSource;
    
    public People create(People people){
        return null;
    }
}
