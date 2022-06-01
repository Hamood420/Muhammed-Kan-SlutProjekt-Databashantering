/*Tar bort existerande databas movie, sedan tillägger en ny databas movies*/
drop database if exists movie;
create database if not exists movies;
use movies; 

/*Tar bort existerande tabell director, sedan tillägger en ny tabell director*/
drop table if exists director;
create table if not exists director (director_id int auto_increment, director_name varchar(50) not null, primary key (director_id)
);

/*Tar bort existerande tabell movies, sedan tillägger en ny*/
drop table if exists movies; 
create table if not exists films (movie_id int auto_increment, movie_name varchar(50) not null, director_id int not null, primary key (movie_id), foreign key (director_id) references director (director_id)
); 

/*Insertar ny director namn och efternamn och stad*/
insert into director (director_name) values ('Hamood');
insert into director (director_name) values ('Alemdar');
insert into director (director_name) values ('Hamood');


/*Tillägger filmer till databasen*/
insert into films (movie_name, director_id) values ('Iron Man', 1);
insert into films (movie_name, director_id) values ('Avengers Infinity War', 2);
insert into films (movie_name, director_id) values ('Django Unchained', 3);
insert into films (movie_name, director_id) values ('Wolf of Wall Street', 4);
insert into films (movie_name, director_id) values ('Inception', 5);


/*Joinar director till film databasen*/
select * from films join director on director.director_id = films.director_id;
