package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        ServletHolder holder = handler.addServlet(ServletContainer.class, "/rest/*");
        holder.setInitOrder(1);
        holder.setInitParameter("jersey.config.server.provider.packages", "com.example.controller");

        server.start();
    }

}
