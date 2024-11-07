package hw2.servlet;


import hw2.dao.UserDao;
import hw2.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = new User(0, name, email);
        try {
            userDao.saveUser(user);
            response.getWriter().write("User saved successfully.");
        } catch (SQLException e) {
            response.getWriter().write("Error saving user.");
            e.printStackTrace();
        }
    }
}

