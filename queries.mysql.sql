/*Skapar queries */
use movie;

describe movie;

select movie_title as Title, movie_desc as Description, movie_director as 'Movie Director', movie_writer as 'Movie Writer',movie_release_date as 'Release Date' from movie;

select movie_title from movie where movie_release_date < sysdate();

select movie_title, actor_first_name, actor_last_name, role_desc from movie, actor, role
where movie_id = role_movie_id and role_person_id = actor_id;

alter table movie change movie_desc movie_plot varchar(150);