package bean;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class directorBean {
	private String name;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		String pattern = "Namn %s, Ålder = %d, Stad = %s";
		String returnString = String.format(pattern, this.name, this.id);
		
		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Name", this.name));
		dataList.add(new keyvaluepair("Age", Integer.toString(this.id)));
		
		return jsonHelper.toJsonObject(dataList);
	}
	
}