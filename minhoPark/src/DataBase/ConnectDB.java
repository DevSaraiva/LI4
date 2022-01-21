package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.*;

    public class ConnectDB {
        private Connection connection;

        public static Connection getConnection(){
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/minhoPark","root","password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void stopConnection(Connection connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("aqui");
                e.printStackTrace();
            }
        }

        public static void test(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM parques");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Nome"));
            }
        }
    }

