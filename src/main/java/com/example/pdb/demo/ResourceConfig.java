package com.example.pdb.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
public class ResourceConfig extends org.glassfish.jersey.server.ResourceConfig {

    public ResourceConfig() {
        register(HW.class);
    }
    
    
    @Path("test")
    public static class HW {
        
        @GET
        public String test() { 
            return "Hello test";
        }
    }
}
