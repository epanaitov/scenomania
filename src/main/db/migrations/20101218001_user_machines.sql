create table user_machines (
	id int unsigned not null auto_increment primary key,
	user_id int unsigned not null,
	machine_hash text not null
);