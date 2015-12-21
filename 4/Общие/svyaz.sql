/*create view svyaz_tables as select route_station.st_func as func,
 station.name as name, route_station.num_in_route as num
 from route_station, station
 where route_station.st_id=station.st_id; */
create view svyaz_tables1 as select place.carriage as carriage,
place.num_in_car as num, ticket.status, train."TYPE" from ticket, place, train
where place.place_id=ticket.place_id and train.train_id=place.train_id;
commit;

