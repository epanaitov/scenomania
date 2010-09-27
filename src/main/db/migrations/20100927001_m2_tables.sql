create table stages (
	id int unsigned not null auto_increment primary key,
	city_id int unsigned not null,
	index using btree (city_id),
	name varchar(255),
	description mediumtext,
	reputation int,
	latitude double,
	longitude double	
) character set utf8;

create table events (
	id int unsigned not null auto_increment primary key,
	promoter_id int unsigned not null,
	index using btree (promoter_id),
	stage_id int unsigned not null,
	index using btree (stage_id),
	name varchar(255),
	description mediumtext,
	start datetime,
	end datetime
) character set utf8;

create table performances (
	id int unsigned not null auto_increment primary key,
	tour_id int unsigned not null,
	index using btree (tour_id),
	event_id int unsigned not null,
	index using btree (event_id)
);

create table tours (
	id int unsigned not null auto_increment primary key,
	name varchar(255),
	description mediumtext,
	band_id int unsigned not null,
	index using btree (band_id),
	status tinyint
) character set utf8;

create table comments (
	id int unsigned not null auto_increment primary key,
	body mediumtext,
	entity_type varchar(255) not null,
	entity_id int unsigned not null,
	index using btree (entity_id, entity_type(20)),
	user_id int unsigned not null,
	index using btree (user_id)
) character set utf8;

create table tags (
	id int unsigned not null auto_increment primary key,
	name varchar(50),
	index using btree (name)
) character set utf8;