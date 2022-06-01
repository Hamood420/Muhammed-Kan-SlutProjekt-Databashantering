package objecktLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.actorBean;
import helpers.jsonHelper;

public class actors {
	private Connection _connection;
	private ArrayList<actorBean>_actors;
	private String SelectAllActors = "select * from actor";
	private String UpdateActors = "Update actor SET hometown = ?, actor_age = ? Where actor_name = ?;";
	private String UpdateActorsShort = "Update actor set hometown = ? Where actor_name = ?;";
	private String UpdateActorCity = "Update actor SET hometown = ? Where hometown Like ?;";
	private String UpdateActorsAge = "Update actor set age=? Where name=?";
	private String deleteActors = "Delete from actor Where name=?";

	
	public actors(Connection CN) {
		this._connection = CN;
		this._actors = new ArrayList<actorBean>();
		getActors();
	}
	
	
	public ArrayList<actorBean> getActors() {
		String query = "Select * from actor";
		
		if (this._actors.size() > 0)
			return this._actors;
		
		this._actors = new ArrayList<actorBean>();
		try (PreparedStatement myQuery = this._connection.prepareStatement(query)){
			runQuery(myQuery);
		} catch (SQLException e) {
			System.out.println("GetActors execption for statement");
			e.printStackTrace();
		}
		
		return this._actors;
	}

	
	public String toJson() {
		String beansContent = "";
		for(actorBean AB : this._actors) {
			beansContent += AB.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Actors", beansContent);
	}
	
	private actorBean buildActor(ResultSet RS) {
		actorBean AB = new actorBean();
		
		try {
			AB.setHometown(RS.getString("hometown"));
			AB.setAge(RS.getInt("actor_age"));
			AB.setName(RS.getString("actor_name"));
			AB.setId(RS.getInt("actor_id"));
			AB.setAddressId(RS.getInt("address_id"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return AB;
	}
	
	private void buildActors(ResultSet RS) throws SQLException {
		while(RS.next()) {
			this._actors.add(buildActor(RS));
		}
	}
	
	private void runQuery(PreparedStatement query) {
		try (ResultSet RS = query.executeQuery()) {
				buildActors(RS);
	} catch (SQLException e) {
		System.out.println("GetActors execption for result set");
		e.printStackTrace();
		}
	}


	public int createActors(String name, int age) {
		int count = -1;
		
		if(age < 0 || age > 80) return -1;
		if(name.isBlank()) return -1;
		
		try(PreparedStatement myQuery = this._connection.prepareCall(UpdateActors)) {
			myQuery.setString(1, name);
			myQuery.setInt(2, age);
			
			count = myQuery.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("GetActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int updateActor(String name, int newAge, String function) {
		int count = -1;
		
		if ((newAge<0 || newAge>150)) return -1;
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(UpdateActors)) {
				
			
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	


	public int UpdateActorsCity(String oldCity, String newCity) {
		int count = -1;
		
		try (PreparedStatement myQuery = this._connection.prepareStatement(UpdateActorCity)) {
			myQuery.setString(1, newCity);
			myQuery.setString(2, oldCity);
			count = myQuery.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("UpdateActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	public int deleteAnActor(String name) {
		
		int count = -1;
		try (PreparedStatement myQuery = this._connection.prepareStatement(deleteActors)) {
			myQuery.setString(1, name);
			count = myQuery.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DeleteActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	
}