/*Skapar views för movie.title och genre*/
use movies;

create view moviegenres as 
select movie.title, group_concat(genre.genre separator '-') as "genres" from movies
join moviegenres on movies._id = moviegenres.movie_id join genre on moviegenres.genre_id = genre.genre_id
group by movies_title