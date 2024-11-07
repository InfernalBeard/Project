package hw2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import hw2.servlet.UserServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/"); // The root context

        contextHandler.addServlet(new ServletHolder(new UserServlet()), "/user");

        server.setHandler(contextHandler);

        System.out.println("Starting server on port 8080...");
        server.start();

        server.join();
    }
}
