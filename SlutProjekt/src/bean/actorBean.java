package bean;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class actorBean {
	private String _name;
	private int _id;
	private String _hometown;
	private int _age;
	private int _addressId;
	
	public void setId(int id) {
		this._id = id;
	}
	
	public int getId() {
		return this._id;
	}
	
	public void setName(String name) {
		this._name = name;
	}

	public String getName() {
		return this._name;
	}
	
	public void setHometown(String hometown) {
		this._hometown = hometown;
	}
	
	public String getHometown() {
		return this._hometown;
	}
	
	public void setAge(int age) {
		this._age = age;
	}
	
	public int getAge() {
		return this._age;
	}
	
	public void setAddressId(int addressId) {
		this._addressId = addressId;
	}
	
	public int getAddressId() {
		return this._addressId;
	}
	
	public String toString() {
		String pattern = "Namn %s, Ålder = %d, Stad = %s";
		String returnString = String.format(pattern, this._name, this._age, this._hometown);
		
		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Name", this._name));
		dataList.add(new keyvaluepair("Age", Integer.toString(this._age)));
		dataList.add(new keyvaluepair("City", this._hometown));
		
		return jsonHelper.toJsonObject(dataList);
	}
}