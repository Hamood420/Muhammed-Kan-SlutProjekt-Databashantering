package objecktLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.genreBean;
import helpers.jsonHelper;

public class genres {
	
	private Connection _connection;
	private ArrayList<genreBean> _genres;
	
	private String createGenre = "INSERT INTO genre (genre) VALUES (?) ON DUPLICATE KEY UPDATE genre_id=genre_id";
	private String readAllGenre = "SELECT * FROM genre";
	private String updateGenre = "UPDATE genre SET genre=? WHERE genre=?";
	private String deleteGenre = "DELETE FROM genre WHERE genre=?";
	
	public genres(Connection cn) {
		this._connection = cn;
		this._genres = new ArrayList<genreBean>();
		get_genres();

	}
	
	public int createGenre(String genre) {
		int count = -1;
		
		if (genre.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(createGenre)) {
			myQry.setString(1, genre);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("CreateGenre exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<genreBean> get_genres() {
		
		try (PreparedStatement myQry = this._connection.prepareStatement(readAllGenre)) { 
			try (ResultSet rs = myQry.executeQuery()) {
				
				while(rs.next()) {  // rows
					genreBean GB = new genreBean();
					GB.setId(rs.getInt("genre_id"));
					GB.setGenre(rs.getString("genre"));
					
					this._genres.add(GB);
				}
				
			} catch (SQLException e) {
				System.out.println("getGenres exception for result set");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("getGenres exception for statement");
			e.printStackTrace();
		}
		
		return _genres;
	}
	
	public int updateGenre(String genre, String newGenre) {
		int count = -1;
		
		if (genre.isBlank() || newGenre.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(updateGenre)) {
			myQry.setString(1, newGenre);
			myQry.setString(2, genre);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateGenre exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteGenre(String genre) {
		int count = -1;
		
		if (genre.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteGenre)) {
			myQry.setString(1, genre);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteGenre exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (genreBean gb : this._genres) {
			beansContent += gb.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Genres", beansContent);
	}
	
}