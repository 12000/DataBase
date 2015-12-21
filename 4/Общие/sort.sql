create view v_sort as  select * from route_station
order by route_id asc, st_id asc;
commit;

