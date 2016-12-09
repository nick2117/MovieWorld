package com.hello.restjava;
import java.sql.*;
 

public class TestConnection {
	
	static Connection con;
    static{
        try {
            Class.forName("org.sqlite.JDBC");
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
   public static Connection getconnection()
    {
        try {
            con=DriverManager.getConnection("jdbc:sqlite:/home/consultadd/Software/movieDB.db");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
//	TestConnection()
//	{
//	   
//	        try {
//	            Class.forName("org.sqlite.JDBC");
//	         //   String dbURL = "jdbc:sqlite:movieWorld.db";
//	           // Connection conn = DriverManager.getConnection(dbURL);
//	            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/consultadd/Software/movieWorld.db");
//	            if (conn != null) {
//	                System.out.println("Connected to the databasekjlkjklj");
//	                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//	                System.out.println("Driver name: " + dm.getDriverName());
//	                System.out.println("Driver version: " + dm.getDriverVersion());
//	                System.out.println("Product name: " + dm.getDatabaseProductName());
//	                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//	               
//	                Statement statement = conn.createStatement();
//	                
//	            }
//	                ResultSet resultSet = statement.executeQuery("select movies.movie_ID,movies.movie_name, count(movies.movie_id) as count from users join movies on users.user_id =movies.user_id where users.user_id=1 group by movie_id;");
//	               
//	                // Json.parse(resultSet);
//	                System.out.println(resultSet);
//	               
//	                while(resultSet.next())
//	                {
//	                	System.out.println("good");
//	                   // iterate & read the result set
//	                   System.out.println("movie_name = " + resultSet.getString("movie_name"));
//	                   System.out.println("movie_id = " + resultSet.getInt("movie_id"));
//	                   System.out.println("count = " + resultSet.getInt("count"));
//	                }
//	                conn.close();
//	            }
//	        } catch (ClassNotFoundException ex) {
//	            ex.printStackTrace();
//	        } catch (SQLException ex) {
//	            ex.printStackTrace();
//	        }
	    }


