package hw2.dao;

import hw2.model.User;
import hw2.util.DatabaseConnectionManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserDaoTest {

    private UserDao userDao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private DatabaseConnectionManager mockDbConnectionManager;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockDbConnectionManager = mock(DatabaseConnectionManager.class);
        when(mockDbConnectionManager.getConnection()).thenReturn(mockConnection);

        userDao = new UserDao();
    }

    @Test
    public void testSaveUser_success() throws SQLException {
        User user = new User(0, "John Doe", "johndoe@example.com");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doNothing().when(mockPreparedStatement).executeUpdate();

        userDao.saveUser(user);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testSaveUser_sqlException() throws SQLException {
        User user = new User(0, "John Doe", "johndoe@example.com");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Database error"));

        try {
            userDao.saveUser(user);
        } catch (SQLException e) {
            assert (e.getMessage().equals("Database error"));
        }

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}

