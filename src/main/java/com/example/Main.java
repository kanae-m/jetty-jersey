package com.example;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // contextPath で ServletContextHandler を作成します。
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        // ServletContainer をコンテキストに追加します。
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/api/*");
        // init-parameters でサーブレットを設定します。
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.example.resources");

        // CSRF 攻撃から保護するために CrossOriginFilter を追加します。
        FilterHolder filterHolder = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        // フィルターを設定します。
        filterHolder.setAsyncSupported(true);

        // コンテキストをサーバーにリンクします。
        server.setHandler(context);

        server.start();
    }

}
