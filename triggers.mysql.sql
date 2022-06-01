/*Skapar queries*/

use movies;

insert into address (address, postalcode, state) VALUES ('Ringvägen 31', 232, 'Vastergotaland');

insert into actor (actor_name, actor_age, address_id)
select 'Hamodi Smith',  69, address_id from address where postalcode = 232 