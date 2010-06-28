create table city_locale(
	id int unsigned not null auto_increment primary key,
	city_id int unsigned not null,
	name varchar(255),
	description text,
	locale char(2)
);