create procedure find_route(p_id int)
returns(r_id int)
as begin
for
    select shedule.route_id from shedule, place where
        shedule.train_id = place.train_id and place.place_id = :p_id
into :r_id do suspend;
end;

