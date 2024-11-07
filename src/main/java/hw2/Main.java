package hw2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import hw2.servlet.UserServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server instance, running on port 8080
        Server server = new Server(8080);

        // Create a ServletContextHandler to map servlets
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/"); // The root context

        // Map UserServlet to handle POST requests at "/user"
        contextHandler.addServlet(new ServletHolder(new UserServlet()), "/user");

        // Set the context for the server
        server.setHandler(contextHandler);

        // Start the server
        System.out.println("Starting server on port 8080...");
        server.start();

        // Join the server thread to wait for it to finish (it will run until interrupted)
        server.join();
    }
}
