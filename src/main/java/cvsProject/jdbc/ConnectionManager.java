package cvsProject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public static Connection connectToDB() {
        String url = "jdbc:mysql://localhost:3306/user_db?serverTimezone=GMT";
        String username = "root";
        String password = "root";

        try{
            connection = DriverManager.getConnection(url, username,password);

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
        }catch (Exception e){

        }
    }
}
