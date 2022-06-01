package bean;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class genreBean {
	private String genre;
	private int id;

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		String pattern = "Genre = %s";
		String text = String.format(pattern, this.getGenre());	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Genre", this.getGenre()));
		

		return jsonHelper.toJsonObject(datalist);
	}
}