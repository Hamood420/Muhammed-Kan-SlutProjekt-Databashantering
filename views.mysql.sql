/*Skapar views*/
use movie;
create view movie_v_genre as
  select movie_title, genre_type
  from movie, genre, movie_genre
  where movie_id = genre_movie_id and genre_genre_id = genre_id;
  
  create view movie_v_actors as
  select movie_title, actor_first_name, actor_last_name, role_desc
  from movie, actor, role
  where movie_id = role_movie_id and role_person_id = actor_id;

