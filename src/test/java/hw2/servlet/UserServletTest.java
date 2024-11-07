package hw2.servlet;

import hw2.dao.UserDao;
import hw2.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServletTest {

    private UserDao mockUserDao;
    private UserServlet userServlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;

    @BeforeEach
    public void setUp() {
        mockUserDao = mock(UserDao.class);

        userServlet = new UserServlet();
        userServlet.userDao = mockUserDao;

        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
    }

    @Test
    public void testDoPost_success() throws Exception {
        String name = "John Doe";
        String email = "johndoe@example.com";

        when(mockRequest.getParameter("name")).thenReturn(name);
        when(mockRequest.getParameter("email")).thenReturn(email);

        doNothing().when(mockUserDao).saveUser(any(User.class));

        userServlet.doPost(mockRequest, mockResponse);

        verify(mockResponse).getWriter();
        verify(mockResponse.getWriter()).write("User saved successfully.");

        verify(mockUserDao, times(1)).saveUser(any(User.class));
    }

    @Test
    public void testDoPost_failure() throws Exception {
        String name = "John Doe";
        String email = "johndoe@example.com";

        when(mockRequest.getParameter("name")).thenReturn(name);
        when(mockRequest.getParameter("email")).thenReturn(email);

        doThrow(new SQLException("Database error")).when(mockUserDao).saveUser(any(User.class));

        userServlet.doPost(mockRequest, mockResponse);

        verify(mockResponse).getWriter();
        verify(mockResponse.getWriter()).write("Error saving user.");

        verify(mockUserDao, times(1)).saveUser(any(User.class));
    }
}


