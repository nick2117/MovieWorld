package com.hello.restjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class MovieAPI {
	@GET
	@Path("/user/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("userid") String userId) {
		//JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			con = DriverManager
					.getConnection("jdbc:sqlite:/home/consultadd/Software/movieWorld.db");
				} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1);
		}

		try {
			Statement pst = null;

			if (con != null) {
				pst = con.createStatement();
				System.out.println(pst);
			} else {
				System.out.println("create a connection first");
			}

			ResultSet resultSet = pst
					.executeQuery(" select movies.movie_ID,movies.movie_name,rating.rating from users join rating on users.user_id=rating.user_id join movies on rating.movie_id=movies.movie_id where users.user_id="
							+ userId + " group by movies.movie_id");

			while (resultSet.next()) {
				System.out.println("good");

				obj.put("movie_id", resultSet.getInt("movie_id"));
				obj.put("movie_name", resultSet.getString("movie_name"));
				obj.put("rating", resultSet.getInt("rating"));
				//obj.put("count", resultSet.getInt("count"));

				System.out.println(resultSet.getString("movie_name"));

			}
			con.close();
			resultSet.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("UserId is" + userId);

		return obj.toString();// Response.status(200).entity(obj).build();
	}

	@GET
	@Path("/movie/{movieid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMovie(@PathParam("movieid") String movieId) {
		//JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager
					.getConnection("jdbc:sqlite:/home/consultadd/Software/movieWorld.db");
			System.out.println("**connecting*****" + con);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1);
		}

		try {
			System.out.println("************inside get movie*********"
					+ movieId);
			Statement pst = null;

			if (con != null) {
				pst = con.createStatement();
				System.out.println("connected.......");

			} else {
				System.out.println("create a connection first");
			}

			/*ResultSet resultSet = pst
					.executeQuery("select rating.movie_id,movies.movie_name, avg(rating.rating) "
							+ "as ratings from rating join movies on rating.movie_id=movies.movie_id where rating.movie_id="
							+ movieId + " group by rating.movie_id");*/
			
			ResultSet resultSet = pst
					.executeQuery("select rating.movie_id,movies.movie_name, avg(rating.rating)as ratings from rating join movies on rating.movie_id=movies.movie_id where rating.movie_id="+movieId+";");

			while (resultSet.next()) {
				System.out.println("good");

				obj.put("movie_id", resultSet.getInt("movie_id"));
				obj.put("rating", resultSet.getInt("ratings"));
				obj.put("movie_name", resultSet.getString("movie_name"));
		
			}
			con.close();
			resultSet.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj.toString();// Response.status(200).entity(obj).build();
	}

	@GET
	@Path("/movieGenre/{userid}/{genre}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMovie(@PathParam("userid") String userId,
			@PathParam("genre") String genre) {
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager
					.getConnection("jdbc:sqlite:/home/consultadd/Software/movieWorld.db");
			System.out.println("**connecting*****" + con);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}

		try {
			System.out
					.println("************inside get movie*********" + userId);
			Statement pst = null;

			if (con != null) {
				pst = con.createStatement();
				System.out.println("connected.......");

			} else {
				System.out.println("create a connection first");
			}

			ResultSet resultSet = pst
					.executeQuery("select movies.movie_ID,movies.movie_name,movies.genre,rating.rating,users.user_name from users join rating on users.user_id=rating.user_id join movies on rating.movie_id=movies.movie_id where users.user_id="
							+ userId + " group by movies.movie_id");

			while (resultSet.next()) {
				System.out.println("good");

				obj.put("user_name", resultSet.getString("user_name"));
				obj.put("rating", resultSet.getInt("rating"));
				obj.put("movie_name", resultSet.getString("movie_name"));
				obj.put("genre", resultSet.getString("genre"));

		
			}
			con.close();
			resultSet.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj.toString();
	}

}
