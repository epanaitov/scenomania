alter table user_machines add index `user_id_index` (`user_id`);
alter table user_machines add unique `user_machine_unique` (`user_id`, `machine_hash`(300));