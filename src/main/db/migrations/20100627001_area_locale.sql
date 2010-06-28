create table area_locale (
	id int unsigned not null auto_increment primary key,
	area_id int unsigned,
	name varchar(255),
	locale char(2)
);