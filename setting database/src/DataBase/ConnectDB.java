package DataBase;


import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



import java.sql.*;

    public class ConnectDB implements Serializable{
        private Connection connection;

        public static Connection getConnection(){


            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/minhoPark?autoReconnect=true&useSSL=false","root","password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void stopConnection(Connection connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }






        public static String convertToFile(Blob blob, String filename) {

            if(blob == null) return null;

            String filepath = null;

            try {

                String currentPath = null;
                try {
                    currentPath = new File(".").getCanonicalPath();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InputStream is = blob.getBinaryStream();

                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayOutputStream buf = new ByteArrayOutputStream();

                int result;

                result = bis.read();

                while (result != -1) {
                    buf.write((byte) result);
                    result = bis.read();
                }

                 filepath = currentPath + "/app/src/main/java/com/example/imgs/" + filename + ".jpg";

                File file = new File(filepath.trim());
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(buf.toByteArray());
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
                return  filepath;
        }




        public static void test(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM parques");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Nome"));
            }
        }

    }

