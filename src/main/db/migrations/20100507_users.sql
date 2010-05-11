CREATE TABLE users (
	id int unsigned not null auto_increment primary key,
	email varchar(255),
	nickname varchar(255),
	password varchar(255),
	salt varchar(255),
	created_at datetime,
	last_login datetime
);