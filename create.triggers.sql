/*Skapar triggers*/
use movies;
delimiter $$
create trigger ins_transaction 
before insert on actor for each row 
set new.actor_age = new.actor_age + 1;