create table promoters_users(
	id int unsigned not null auto_increment primary key,
	promoter_id int unsigned not null,
	user_id int unsigned,
	position varchar(255)
) CHARACTER SET utf8;