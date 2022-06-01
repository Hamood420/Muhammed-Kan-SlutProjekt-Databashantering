package objecktLists;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.filmsBean;
import helpers.jsonHelper;

public class films {

	private Connection _connection;
	private ArrayList<filmsBean> _movies;
	
	private String createMovie = "{call addMovie(?, ?, ?, ?)}";
	private String readAllMovies = "Select * from movie";
	private String updateMovieLength = "Update movie set lenght=? Where title=?";
	private String deleteMovie = "Delete from movie Where title=?";

	
	public films(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<filmsBean>();
		getMovies();
		
	}
	

	public int createMovie(
			String movie_title,int movie_release_year,int movie_length,
			String director_name) {
		int result = -1;
		
		if (movie_title.isBlank() || 
			movie_release_year<1600 || 
			movie_length==0 || 
			director_name.isBlank()) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createMovie)) {
			cst.setString(1, movie_title);
			cst.setInt(2, movie_release_year);
			cst.setInt(3, movie_length);
			cst.setString(4, director_name);
			result = cst.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("createMovie exception for statement");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<filmsBean> getMovies() {
		if (this._movies.size() > 0) 
			return this._movies;
			
		try (PreparedStatement myQry = this._connection.prepareStatement(readAllMovies)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("GetMovies exception for statement");
			e.printStackTrace();
		}
		
		return this._movies;
	}
	
	
	public String toJson() {
		String beansContent = "";
		for (filmsBean FB : this._movies) {
			beansContent += FB.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Movies", beansContent);
	}

 	private filmsBean buildMovie(ResultSet RS) {
 		filmsBean FB = new filmsBean();

		try {
			FB.setMovieId(RS.getInt("movie_id"));
			FB.setDirectorId(RS.getInt("director_id"));
			FB.setMovieTitle(RS.getString("title"));
			FB.setReleaseYear(RS.getInt("release_year"));
			FB.setLenght(RS.getInt("length"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return FB;
	}
	
 	private void buildMovies(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movies.add(buildMovie(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("getMovies exception for result set");
			e.printStackTrace();
		}

 	}


	public int deleteAnFilm(String movieTitle) {
		int count = -1;
		try(PreparedStatement myQuery = this._connection.prepareStatement(deleteMovie)) {
			myQuery.setString(1, movieTitle);
			
			count = myQuery.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("DeleteFilm exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}


	public int updateMovieLength(int newLenght, Connection conn) {
		int count = -1;
		
		try(PreparedStatement myQuery = this._connection.prepareStatement(updateMovieLength)) {
			myQuery.setInt(newLenght, 1);
			
			count = myQuery.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Update the movie lenght exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
}