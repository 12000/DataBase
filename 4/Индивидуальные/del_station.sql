create procedure del_station as begin
delete from station where st_id
not in (select st_id from route_station);
end;


