package hw2;

public class Constants {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/mydb";
    public static final String DATABASE_USER = "postgres";
    public static final String DATABASE_PASSWORD = "password";

    public static final String USER_INSERT_QUERY = "INSERT INTO users (name, email) VALUES (?, ?)";
    public static final String ROLE_INSERT_QUERY = "INSERT INTO roles (role_name) VALUES (?)";
    public static final String USER_ROLE_INSERT_QUERY = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
}
