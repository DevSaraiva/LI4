package com.example.minhopark.DataBase;


import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



import java.sql.*;

    public class ConnectDB {
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



        public static Blob convertFileContentToBlob(String filePath){

            File fi = new File(filePath);

            Blob blob = null;

            try{
                byte[] fileContent = new byte[0];

                fileContent = Files.readAllBytes(fi.toPath());

                System.out.println(fileContent.length);

                blob = new SerialBlob(fileContent);

            }catch (Exception e){
                e.printStackTrace();
            }

            return blob;
        }




        public static String convertToFile(Blob blob, String filename) {

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

                 filepath = currentPath + "/app/src/main/java/com/example/imgs/" + filename + ".jpeg";

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

