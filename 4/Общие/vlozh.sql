create view vlozh as select
place_id as place_id, num_in_car as num_in_car from place
where place.place_id not in (select place_id from ticket) and place.train_id=75;
commit;

