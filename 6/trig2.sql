/*drop trigger proverka;
drop exception del_mod_err*/

create exception del_mod_err 'Wrond modify! This station is using in table route_station!';

create trigger proverka for station
before delete or update
as begin
    if(old.st_id in (select route_station.st_id from route_station))
    then exception del_mod_err;
end;

