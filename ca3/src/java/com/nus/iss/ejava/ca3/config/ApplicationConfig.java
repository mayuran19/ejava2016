/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.iss.ejava.ca3.config;

import com.nus.iss.ejava.ca3.api.EPodResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author mayuran
 */
@ApplicationPath(value = "/")
public class ApplicationConfig extends Application {
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        // Add additional features such as support for Multipart.
        resources.add(EPodResource.class);
        resources.add(MultiPartFeature.class);
        return resources;
    }
}
