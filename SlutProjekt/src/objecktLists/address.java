package objecktLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.addressBean;
import helpers.jsonHelper;

public class address {
	private Connection _connection;
	private ArrayList<addressBean> _address;

	public address(Connection conn) {
		// TODO Auto-generated constructor stub
	}

	public void addresss(Connection CN) {
		this._connection = CN;
		this._address = new ArrayList<addressBean>();
		getAddress();
	}
	
	public ArrayList<addressBean> getAddress(){
		String query = "select * from address";
		
		if (this._address.size() > 0)
			return this._address;
		
		this._address = new ArrayList<addressBean>();
		try {
			PreparedStatement myQuery = this._connection.prepareStatement(query);
			ResultSet RS = myQuery.executeQuery();
			while(RS.next()) {
				this._address.add(buildAddress(RS));
			}
			
			RS.close();
			myQuery.close();
		} catch (SQLException e) {
			System.out.println("GetActors exception");
			e.printStackTrace();
		}
		
		return this._address;
	}
	
	public String toJson() {
		String beansContent = "";
		for (addressBean AB : this._address) {
			beansContent += AB.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Address", beansContent);
	}
	
	private addressBean buildAddress(ResultSet RS) {
		addressBean AB = new addressBean();
		
		try {
			AB.setState(RS.getString("postalcode"));
			AB.setAddress(RS.getString("address"));
			AB.setId(RS.getInt("address_id"));
			AB.setState(RS.getString("state"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return AB;
		
	}

}