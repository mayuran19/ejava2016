/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca2.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

/**
 *
 * @author Mugunthan
 */
public class HashPasswordGenerator {
    public static String generateHashPassword(String password) {
        String hashedPassword = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
        return hashedPassword;
    }
}
