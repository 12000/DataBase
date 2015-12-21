create view stationVUSL as select * from station where st_id like 1;
create view passengerVUSL as select * from passenger where
pas_id between 36 and 38;
create view trainVUSL as select * from train where train_id in (75, 76);
commit;

