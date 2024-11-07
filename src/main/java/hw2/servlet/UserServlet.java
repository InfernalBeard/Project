package hw2.servlet;

import hw2.dao.UserDao;
import hw2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = new User(0, name, email);

        try {
            logger.info("Attempting to save user with name: {} and email: {}", name, email);

            userDao.saveUser(user);

            logger.info("User saved successfully with name: {} and email: {}", name, email);

            response.getWriter().write("User saved successfully.");
        } catch (SQLException e) {
            logger.error("Error saving user with name: {} and email: {}", name, email, e);

            response.getWriter().write("Error saving user.");
            e.printStackTrace();
        }
    }
}


