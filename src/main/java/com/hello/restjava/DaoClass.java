package com.hello.restjava;
import java.sql.*;
public class DaoClass {

    public DaoClass() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/home/consultadd/Software/movieWorld.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("helloo");
        }
    }

    static Connection connection;
    static PreparedStatement statement;
  
  
    public static void forFirstApi() {
        try {
        	System.out.println("good");
            statement = connection.prepareStatement("select * from movieWorld.users");
//            statement.setInt(1, 1);
//            statement.setString(2, "RM-2014");
//            statement.setString(3, "20140212");
//            statement.setFloat(4, 125.12f);
//            statement.executeUpdate();
            
            
            
            
           // Statement statement = conn.createStatement();
            

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
            	System.out.println("good");
               // iterate & read the result set
               System.out.println("user_name = " + resultSet.getString("user_name"));
               System.out.println("user_id = " + resultSet.getInt("user_id"));
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       // forFirstApi();
    	DaoClass c =new DaoClass();
    	System.out.println(c);
    }
}