import java.sql.Connection;
import java.sql.DriverManager;

public class Storage {
    static Connection connection;
    static int userId;
    public static void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
    }
}
