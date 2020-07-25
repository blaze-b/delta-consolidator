create table employee_master
  (
     id integer not null,
     first_name varchar(100) not null,
     last_name varchar(100) not null,
     date_of_birth date,
     target integer,
	 role_id integer,
     primary key (id)
  );
create sequence employee_master_seq increment by 1 start with 1;
alter table employee_master add column organization varchar(256) not null;