create view vich_pole as
select route_id, st_id, route_id+st_id as summ from route_station;
commit;
