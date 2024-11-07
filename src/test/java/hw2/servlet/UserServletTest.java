package hw2.servlet;

import hw2.dao.UserDao;
import hw2.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
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
        // Мокируем зависимость UserDao
        mockUserDao = mock(UserDao.class);

        // Создаем экземпляр UserServlet и внедряем mockUserDao
        userServlet = new UserServlet();
        userServlet.userDao = mockUserDao;  // Инжектим мокированный UserDao

        // Мокируем объекты запроса и ответа
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
    }

    @Test
    public void testDoPost_success() throws Exception {
        // Подготовка: устанавливаем параметры запроса
        String name = "John Doe";
        String email = "johndoe@example.com";

        when(mockRequest.getParameter("name")).thenReturn(name);
        when(mockRequest.getParameter("email")).thenReturn(email);

        // Мокируем поведение метода saveUser, чтобы он не выполнял реальную операцию
        doNothing().when(mockUserDao).saveUser(any(User.class));

        // Выполняем запрос
        userServlet.doPost(mockRequest, mockResponse);

        // Проверяем, что метод ответа был вызван с ожидаемым сообщением об успехе
        verify(mockResponse).getWriter();
        verify(mockResponse.getWriter()).write("User saved successfully.");

        // Проверяем, что метод saveUser был вызван один раз
        verify(mockUserDao, times(1)).saveUser(any(User.class));
    }

    @Test
    public void testDoPost_failure() throws Exception {
        // Подготовка: устанавливаем параметры запроса
        String name = "John Doe";
        String email = "johndoe@example.com";

        when(mockRequest.getParameter("name")).thenReturn(name);
        when(mockRequest.getParameter("email")).thenReturn(email);

        // Мокируем поведение метода saveUser, чтобы он выбросил исключение
        doThrow(new SQLException("Database error")).when(mockUserDao).saveUser(any(User.class));

        // Выполняем запрос
        userServlet.doPost(mockRequest, mockResponse);

        // Проверяем, что метод ответа был вызван с сообщением об ошибке
        verify(mockResponse).getWriter();
        verify(mockResponse.getWriter()).write("Error saving user.");

        // Проверяем, что метод saveUser был вызван один раз
        verify(mockUserDao, times(1)).saveUser(any(User.class));
    }
}


