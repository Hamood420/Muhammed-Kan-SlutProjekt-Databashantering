package bean;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class filmsBean {
	private String name;
	private int directorId;
	private int movieId;
	private String movieTitle;
	private int releaseYear;
	private int length;
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getLenght() {
		return length;
	}

	public void setLenght(int lenght) {
		this.length = lenght;
	}



	public int getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}



	public String getMovieTitle() {
		return movieTitle;
	}



	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}



	public String getName() {
		return name;
	}
	
	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
	
	public int getDirectorId() {
		return directorId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public String toString() {
		String pattern = "Titel = %s, Release≈r = %d, L‰ngdMinuter = %d";
		String text = String.format(pattern, this.movieTitle, this.releaseYear, this.length);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Title", this.movieTitle));
		datalist.add(new keyvaluepair("ReleaseYear", Integer.toString(this.releaseYear)));
		datalist.add(new keyvaluepair("LengthMinutes", Integer.toString(this.length)));
		
		

		return jsonHelper.toJsonObject(datalist);
	}
}