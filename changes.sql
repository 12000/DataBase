drop table ticket;
create table ticket(t_num INT PRIMARY key,
            place_id INT REFERENCES place(place_id) NOT NULL,
            pas_id INT REFERENCES passenger(pas_id) NOT NULL,
            status char(255) NOT NULL,
            start_st char(255),
            stop_st char(255));
alter table ticket drop pas_id;

insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st)
values(28, 25, 35, 'buy', 'Moscow', 'SPB');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st)
values(35, 128, 37, 'buy', 'Moscow', 'N_Novgorod');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st)
values(44, 244, 39, 'buy', 'SPB', 'Kazan');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st)
values(75, 36, 38, 'wait', 'Vladimir', 'SPB');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st)
values(59, 111, 36, 'wait', 'Moscow', 'Vladimir');

create table t_order(order_id INT PRIMARY key, 
t_num INT references ticket(t_num),
tickets_num INT NOT NULL,
UNIQUE(order_id, t_num));

create table orders(pas_id INT references passenger(pas_id),
order_id INT references t_order(order_id));


create table car_type(car_type_id INT PRIMARY KEY,
name char(255));
create table place_type(place_type_id INT PRIMARY KEY,
name char(255));

insert into car_type(car_type_id, name)
values(0, 'plac');
insert into car_type(car_type_id, name)
values(1, 'coupe');
insert into place_type(place_type_id, name)
values(0, 'high');
insert into place_type(place_type_id, name)
values(1, 'low');
insert into place_type(place_type_id, name)
values(2, 'high side');
insert into place_type(place_type_id, name)
values(3, 'low side');

alter table place drop  car_type;
alter table place drop  pl_pos;

alter table place add car_type_id INT NOT NULL;
alter table place
add constraint place_fk1
foreign key (car_type_id) REFERENCES car_type(car_type_id);

alter table place add place_type_id INT NOT NULL;
alter table place
add constraint place_fk2
foreign key (place_type_id) REFERENCES place_type(place_type_id);
commit;


