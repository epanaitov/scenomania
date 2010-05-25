create table stages (
	id int unsigned not null auto_increment primary key,
	name varchar(255) not null,
	city_id int unsigned not null,
	description text
);