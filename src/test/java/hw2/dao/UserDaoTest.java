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
        // Mocking the DatabaseConnectionManager, Connection, and PreparedStatement
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockDbConnectionManager = mock(DatabaseConnectionManager.class);

        // Mocking the behavior of DatabaseConnectionManager to return the mockConnection
        when(mockDbConnectionManager.getConnection()).thenReturn(mockConnection);

        userDao = new UserDao();
    }

    @Test
    public void testSaveUser_success() throws SQLException {
        // Arrange: Create a user object to be saved
        User user = new User(0, "John Doe", "johndoe@example.com");

        // Mock the behavior of the PreparedStatement to do nothing when executeUpdate() is called
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doNothing().when(mockPreparedStatement).executeUpdate();

        // Act: Call the saveUser method
        userDao.saveUser(user);

        // Assert: Verify that the PreparedStatement's executeUpdate() method was called once
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testSaveUser_sqlException() throws SQLException {
        // Arrange: Create a user object to be saved
        User user = new User(0, "John Doe", "johndoe@example.com");

        // Mock the behavior of the PreparedStatement to throw SQLException
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Database error"));

        // Act & Assert: Verify that an SQLException is thrown
        try {
            userDao.saveUser(user);
        } catch (SQLException e) {
            // Verify that the exception message is as expected
            assert(e.getMessage().equals("Database error"));
        }

        // Verify that executeUpdate was called once
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}

