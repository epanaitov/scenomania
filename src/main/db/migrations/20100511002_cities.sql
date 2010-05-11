create table cities (
	id int unsigned not null auto_increment primary key,
	name varchar(255),
	area_id int unsigned,
	population int unsigned,
	description text,
	reputation int,
	latitude double,
	longitude double
);