package objecktLists;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.actorBean;
import bean.directorBean;
import helpers.jsonHelper;

public class directors {
	private Connection _connection;
	private String SelectAllDirectors = "Select * from director";
	private String createDirector = "Insert director into (name,city) values (?,?) key director_id=director_id";
	private ArrayList<directorBean> _directors;
	private String updateDirectorCity = "Update director set city=? Where name=?";
	private String deleteDirector = "Delete from director Where name=?";


	
	public directors(Connection CN) {
		this._connection = CN;
		this._directors = new ArrayList<directorBean>();
		getDirectors();
	}
	
	public ArrayList<directorBean> getDirectors() {
		String query = "Select * from director";
		if(this._directors.size() > 0)
			return this._directors;
		
		this._directors = new ArrayList<directorBean>();
		try (PreparedStatement myQuery = this._connection.prepareStatement(query)) {
			runQuery(myQuery);
		} catch (SQLException e) {
			System.out.println("GetDirectors exception for statement");
			e.printStackTrace();
		}
		
		return this._directors;
	}
	
	private void runQuery(PreparedStatement query) {
		try (ResultSet RS = query.executeQuery()) {
				buildDirectors(RS);
	} catch (SQLException e) {
		System.out.println("GetDirectors execption for result set");
		e.printStackTrace();
		}
	}
	
	public int createDirector(String name, String city) {
		int count = -1;
		
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQuery = this._connection.prepareStatement(createDirector)) {
			myQuery.setString(1, name);
			myQuery.setString(2, city);
			count = myQuery.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteDirector(String name) {
		int count = -1;
		if (name.isBlank()) return -1;
		
		try(PreparedStatement myQuery = this._connection.prepareStatement(deleteDirector)) {
			myQuery.setString(1, name);
			count = myQuery.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("GetDirector exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public int updateDirector(String name, String newCity) {
		int count = -1;
		
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQuery = this._connection.prepareStatement(updateDirectorCity)) {
			myQuery.setString(1, name);
			myQuery.setString(2, newCity);
			count = myQuery.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("GetActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public String toJson() {
		String beansContent = "";
		for(directorBean DB : this._directors) {
			beansContent += DB.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Directors", beansContent);
	}
	
	private directorBean buildDirector(ResultSet RS) {
		directorBean DB = new directorBean();
		
		try {
			DB.setName(RS.getString("actor_name"));
			DB.setId(RS.getInt("actor_id"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return DB;
	}
	
	private void buildDirectors(ResultSet RS) throws SQLException {
		while(RS.next()) {
			this._directors.add(buildDirector(RS));
		}
	}
	
}