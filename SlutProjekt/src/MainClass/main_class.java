package MainClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bean.actorBean;
import helpers.databaseHelper;
import helpers.jsonHelper;
import objecktLists.actors;
import objecktLists.address;
import objecktLists.directors;
import objecktLists.films;
import objecktLists.genres;

public class main_class {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");
	
		//Skapar en simpel UI 
		UserUI(conn);
		
		conn.close();		
	}
	
	private static void UserUI(Connection conn) {
		
		int select = 999;
		Scanner userSelect = new Scanner(System.in);
		
		while(true)
		{ 
			//En liten meny för användaren
			System.out.println("Hi, Welcome to Movies database");
			System.out.println("Menu");
			System.out.println("0. Show all tables");
			
			System.out.println("1. Create an new actor");
			System.out.println("2. Update actor");
			System.out.println("3. Update an actor age");
			System.out.println("4. Read all actors");
			System.out.println("5. Delete an actor");

			
			System.out.println("6. Create an new director");
			System.out.println("7. Update an actor");
			System.out.println("8. Read all directors");
			System.out.println("9. Delete an actor");
			
			System.out.println("10. Create a new movie");
			System.out.println("11. Update movies");
			System.out.println("12. Read all movies");
			System.out.println("13. Delete all movies");

			System.out.println("14. Create a genre");
			System.out.println("15. Update an genre");
			System.out.println("16. Read all genres");
			System.out.println("17. Delete all genres");
			
			System.out.println("31. To Exit");
			
			
			//För att vara säker att inputen fungerar som det ska
			try {
				select = userSelect.nextInt();
			} catch (Exception e) {
				System.out.println("Unfortunately bad input, please try again");
				userSelect.next();
			}
			
			//Exit
			if(select == 31) {
				System.out.println("Progam have ended, thanks for visting us");
				break;
			}
			
			switch(select) {
			case 1 -> {
				ShowAllTables(conn);
				
				}
				
			case 2 -> {
				System.out.println("Add a new actor to the database");
				System.out.println("Insert the name for actor"); String name = userSelect.next();
				System.out.println("Insert the age for actor"); int age = userSelect.nextInt();
				
				createActors(conn, name, age);
			}
			
			case 3 -> {
				System.out.println("List of all actors");
				readActors(conn);
			}
			
			case 4 -> {
				System.out.println("Update an actor and its age");
				System.out.println("Insert the name for actor"); String newName = userSelect.next();
				System.out.println("Insert the age for actor"); int newAge = userSelect.nextInt();
				
				updateActor(conn, newName, newAge);
			}
			
		
			
			case 6 -> {
				System.out.println("Delete an actor");
				System.out.println("Insert the name for actor"); String name = userSelect.next();
				String Name = userSelect.next();
				
				deleteAnActor(conn, Name);
				
				}
				
			case 7 -> {
				System.out.println("Add a new director to the database");
				System.out.println("Insert the name for director"); String name = userSelect.next();
				System.out.println("Insert the city for director"); String city = userSelect.next();
				
				createDirector(conn, city, name);
			}
			
			case 8 -> {
				System.out.println("List all directors");
				
				readAllDirectors(conn);
			}
			
			case 9 -> {
				System.out.println("Update the director city");
				System.out.println("Insert the name for director"); String name = userSelect.next();
				System.out.println("Insert the new city for director"); String city = userSelect.next();
				
				updateDirectorCity(conn, city, name);
			}
			
			case 10 -> {
				System.out.println("Delete an director");
				System.out.println("Insert the name for director"); String name = userSelect.next();
				
				deleteAnDirector(conn, name);
			}
			
			case 11 -> {
				System.out.println("Add new genres to the database");
				System.out.println("Insert genre name"); String genre = userSelect.next();
				
				createGenre(conn, genre);
			}
			
			case 12 -> {
				System.out.println("List all genres");
				
				readAllGenres(conn);
			}
			
			case 13 -> {
				System.out.println("Update an genre");
				System.out.println("Insert genre name"); String genre = userSelect.next();
				System.out.println("Insert a new genre"); String newGenre = userSelect.next();

				UpdateGenres(conn, genre, newGenre);
			}
			
			case 14 -> {
				System.out.println("Dele");
			}
			
			case 15 -> {
				System.out.println("Delete a genre");
				System.out.println("Insert genre name"); String genre = userSelect.next();
				
				deleteAnGenre(conn, genre);
			}
			
			case 16 -> {
				System.out.println("Create a new movie");
				System.out.println("Insert the movie title"); String movieTitle = userSelect.next();
				System.out.println("Director for movie"); String director = userSelect.next();
				System.out.println("Movie length"); int lenght = userSelect.nextInt();
				System.out.println("Release year"); int releaseYear = userSelect.nextInt();
				System.out.println("Actor for movie"); String actor = userSelect.next();
				System.out.println("Movie Genre"); String genre = userSelect.next();
				
				createFilms(conn, movieTitle, director, lenght, releaseYear, actor, genre);
				
			}
			
			case 17 -> {
				System.out.println("Read all movies");
				
				readAllFilms(conn);
			}
			
			case 18 -> {
				System.out.println("Update movie lenght");
				System.out.println("Insert the movie title"); String movieTitle = userSelect.next();
				System.out.println("New movie lenght"); int newLenght = userSelect.nextInt();
				
				updateFilmLength(movieTitle, newLenght, conn);
			}
			
			case 19 -> {
				System.out.println("Delete a movie");
				System.out.println("Insert the movie title"); String movieTitle = userSelect.next();
				
				deleteAnFilm(movieTitle, conn);
				
				}
			
			default -> System.out.println("0");
				
			}
			
		}
	}
	

	private static void ShowAllTables(Connection conn) {
		actors myActors = new actors(conn);
		directors myDirectors = new directors(conn);
		genres myGenres = new genres(conn);
		films myMovies = new films(conn);
		
		ArrayList<String> document = new ArrayList<String>();
		document.add(myActors.toJson());
		document.add(myDirectors.toJson());
		document.add(myGenres.toJson());
		document.add(myMovies.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);

		System.out.println(jsonDoc);
	}
	
	private static void createGenre(Connection conn, String genre) {
		genres myGenre = new genres(conn);
		
		int AmCreated = myGenre.createGenre(genre);
		System.out.println("The amount of created genres : " + AmCreated);
	}
	
	private static void createActors(Connection conn, String name, int age) {
		actors myActors = new actors(conn);
		
		int AmCreated = myActors.createActors(name, age);
		System.out.println("The amount of created actors: " + AmCreated);
	}
	
	private static void readActors(Connection conn) {
		actors myActors = new actors(conn);
		String jsonDoc = "{" + myActors.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	private static void updateActor(Connection conn, String newName, int newAge) {
		actors myActors = new actors(conn);
		
		int AmUpdates = myActors.updateActor(newName, newAge, "all");
		System.out.println("The amount of updated actors");
	}
	
	
	private static void deleteAnActor(Connection conn, String name) {
		actors myActor = new actors(conn);
		
		int AmDeleted = myActor.deleteAnActor(name);
		System.out.println("The amount of deleted actors:" + AmDeleted);	
	}
	
	private static void createDirector(Connection conn, String name, String city) {
		directors myDirectors = new directors(conn);
		String jsonDoc = "{" + myDirectors.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	private static void updateDirectorCity(Connection conn, String name, String newCity) {
		directors myDirector = new directors(conn);

		int AmUpdates = myDirector.updateDirector(name, newCity);
		System.out.println("The amount  of updated directors : " + AmUpdates);
				
	}
	
	private static void deleteAnDirector(Connection conn, String name) {
		directors myDirector = new directors(conn);
		
		int AmDeleted = myDirector.deleteDirector(name);
		System.out.println("The amount of deleted directors : " + AmDeleted);
	
	}
	
	private static void deleteAnGenre(Connection conn, String genre) {
		genres myGenre = new genres(conn);
		
		int AmDeleted = myGenre.deleteGenre(genre);
		System.out.println("The amount of deleted genres:  " + AmDeleted);
	}
	
	private static void readAllDirectors(Connection conn) {
		directors myDirector = new directors(conn);
		
		String jsonDoc = "{" + myDirector.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	private static void readAllGenres(Connection conn) {
		genres myGenre = new genres(conn);
		
		String jsonDoc = "{" + myGenre.toJson() + "}";
		
		System.out.println("");
	}
	
	private static void UpdateGenres(Connection conn, String genre, String newGenre) {
		genres myGenre = new genres(conn);
		
		int AmUpdates = myGenre.updateGenre(genre, newGenre);
		System.out.println("The amount of updated genres: " + AmUpdates);
	}
	
	private static void createFilms(Connection conn, String movieTitle, String director, int lenght, int releaseYear, String actor, String genre) {
		films myFilms = new films(conn);
		
		String jsonDoc = "{" + myFilms.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	private static void readAllFilms(Connection conn) {
		films myFilms = new films(conn);
		
		String jsonDoc = "{" + myFilms.toJson() + "}";
		
		System.out.println("");
	}
	
	private static void updateFilmLength(String movieTitle, int newLenght, Connection conn) {
		films myFilms = new films(conn);
		
		int AmUpdates = myFilms.updateMovieLength(newLenght, conn);
		System.out.println("The amount of updated genres: " + AmUpdates);
	}
	
	private static void deleteAnFilm(String movieTitle, Connection conn) {
		films myFilms = new films(conn);
		
		int AmDeleted = myFilms.deleteAnFilm(movieTitle);
		System.out.println("The amount of deleted films:  " + AmDeleted);
	}
		
}