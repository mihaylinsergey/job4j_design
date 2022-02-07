/*create table cars(
	id serial primary key,
	model varchar(255),
	year numeric,
	available boolean
);*/
insert into cars(model, year, available) values('Lada','01.01.1990','true');
update cars set model = 'UAZ';
select*from cars;
delete from cars;