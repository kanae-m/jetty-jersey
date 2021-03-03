package com.example;

import jakarta.servlet.DispatcherType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.EnumSet;

public class TestUtil {

    public static Server createServer() {
        Server server = new Server(8081);
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.example.resources");
        FilterHolder filterHolder = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        filterHolder.setAsyncSupported(true);
        server.setHandler(context);
        return server;
    }

    public static WebTarget createTarget() {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8081/api");
    }

}
