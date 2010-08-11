create table country_locale (
	id int unsigned not null auto_increment primary key,
	country_id int unsigned,
	name varchar(255),
	locale char(2)
);