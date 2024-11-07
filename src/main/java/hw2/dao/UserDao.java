package hw2.dao;


import hw2.model.User;
import hw2.util.DatabaseConnectionManager;
import hw2.constants.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public void saveUser(User user) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.USER_INSERT_QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        }
    }
}
