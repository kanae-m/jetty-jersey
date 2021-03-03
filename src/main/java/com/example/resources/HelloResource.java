package com.example.resources;

import com.example.service.HelloService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
public class HelloResource {

    private final HelloService service;

    public HelloResource() {
        service = new HelloService();
    }

    @GET
    public String hello() {
        return service.hello();
    }

}
