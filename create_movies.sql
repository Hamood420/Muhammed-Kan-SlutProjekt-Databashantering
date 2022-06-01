/*Skapar tabeller för movies, moviegenres, actor*/
drop table if exists genre;
create table if not exists genre( genre_id int auto_increment, genre varchar(20) not null unique, primary key (genre_id)
);

drop table if exists moviegenres;
create table if not exists moviegenres(movie_id int not null, genre_id int not null, foreign key (movie_id) references films (movie_id), foreign key (genre_id) references genre (genre_id)
);
drop table if exists actor;
create table if not exists actor(actor_id int auto_increment, actor_name varchar(50) not null, hometown varchar(50) default 'Arlov', actor_age tinyint(3) not null, primary key (actor_id),
Check ( actor_age >= 18 AND actor_age < 67)
);

drop table if exists address;
create table if not exists address(address_id int auto_increment, addresss varchar (50) not null, postalcode smallint not null, state varchar(50) default 'Sverige',
primary key (address_id),
Check (state != 'VasterGotaland')
);