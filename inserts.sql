insert into passenger (pas_id, name, surname, document, doc_num)
values (740, 'Ivan', 'Ivanov', 'passport', 9408255255);


select pas_id from passenger;


insert into station(st_id, type) values(1, 'MOSCOW');
insert into station(st_id, type) values(2, 'SPB');
insert into station(st_id, type) values(3, 'N_Novgorod');
insert into station(st_id, type) values(4, 'Vladimir');
insert into station(st_id, type) values(5, 'Kazan');

insert into passenger(pas_id, name, surname, document, doc_num) values(35,
'Ivan', 'Ivanov', 'passport', '9408255255');
insert into passenger(pas_id, name, surname, document, doc_num) values(36,
'Petr', 'Petrov', 'passport', '9408244244');
 insert into passenger(pas_id, name, surname, document, doc_num) values(37,
'Alexander', 'Belov', 'militaryID', '45286513');
insert into passenger(pas_id, name, surname, document, doc_num) values(38,
'Vladimir', 'Krasnov', 'passport', '9410235235');
insert into passenger(pas_id, name, surname, document, doc_num) values(39,
'Dmitriy', 'Chernov', 'passport', '9411288288');

insert into train(train_id, type, carriages) values(75, '2EL5', 15);
insert into train(train_id, type, carriages) values(76, '2EL5', 12);
insert into train(train_id, type, carriages) values(77, 'KZ4A', 10);
insert into train(train_id, type, carriages) values(78, 'VL40U', 13);
insert into train(train_id, type, carriages) values(79, 'E5K', 11);

insert into place(place_id, train_id, carriage, num_in_car, car_type, pl_po
s) values(25, 75, 1, 25, 'plac', 'low');
insert into place(place_id, train_id, carriage, num_in_car, car_type, pl_po
s) values(111, 76, 3, 3, 'plac', 'low');
 insert into place(place_id, train_id, carriage, num_in_car, car_type, pl_po
s) values(128, 77, 3, 20, 'plac', 'high');
insert into place(place_id, train_id, carriage, num_in_car, car_type, pl_po
s) values(36, 78, 1, 36, 'coupe', 'high');
insert into place(place_id, train_id, carriage, num_in_car, car_type, pl_po
s) values(244, 79, 5, 12, 'coupe', 'high');

insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st) valu
es(28, 25, 35, 'buy', 'Moscow', 'SPB');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st) valu
es(35, 128, 37, 'buy', 'Moscow', 'N_Novgorod');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st) valu
es(44, 244, 39, 'buy', 'SPB', 'Kazan');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st) valu
es(75, 36, 38, 'wait', 'Vladimir', 'SPB');
insert into ticket(t_num, place_id, pas_id, status, start_st, stop_st) valu
es(59, 111, 36, 'wait', 'Moscow', 'Vladimir');

insert into shedule(route_id, train_id, name, r_date)
values(29, 75, '722A Moscow-SPB', '12:15 24.12.2015');
insert into shedule(route_id, train_id, name, r_date)
values(125, 77, '138 Moscow-N_Novgorod', '23:01 25.12.2015');
insert into shedule(route_id, train_id, name, r_date)
values(158, 79, '592 SPB-Kazan', '16:53 26.12.2015');
insert into shedule(route_id, train_id, name, r_date)
values(232, 78, '235 Vladimir-SPB', '01:33 24.12.2015');
insert into shedule(route_id, train_id, name, r_date)
values(38, 76, '199 Moscow-Vladimir', '10:15 23.12.2015');


insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(1, 29, 'start', '-', 1);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(2, 29, 'finish', '-', 4);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(2, 158, 'start', '-', 1);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(5, 158, 'finish', '-', 29);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(1, 125, 'start', '-', 1);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(3, 125, 'finish', '-', 11);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(4, 232, 'start', '-', 1);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(2, 232, 'finish', '-', 12);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(1, 38, 'start', '-', 1);
insert into route_station(st_id, route_id, st_func, stop_time, num_in_route)
values(4, 38, 'finish', '-', 17);
commit;

