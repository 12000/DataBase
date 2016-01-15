drop trigger valid_st;
drop exception valid_warning;

create exception valid_warning 'Wrong stations in ticket!';

create trigger valid_st for ticket
before insert
as
declare variable r_id int;
declare variable sstop_id int;
declare variable sstart_id int;
begin
     execute procedure find_route(new.place_id)
        returning_values r_id;

     select station.st_id from station where station.name = new.start_st
     into :sstart_id;

     select station.st_id from station where station.name = new.stop_st
     into :sstop_id;

     if (
     (:sstop_id not in (select route_station.st_id from route_station where
     route_station.route_id = :r_id))
     or
     (:sstart_id not in (select route_station.st_id from route_station where
     route_station.route_id = :r_id))
     or
     ((select route_station.num_in_route from route_station where
        route_station.st_id = :sstart_id and route_station.route_id = :r_id) >
     (select route_station.num_in_route from route_station where
      route_station.st_id = :sstop_id and route_station.route_id = :r_id))
     ) then  exception valid_warning;

end;

