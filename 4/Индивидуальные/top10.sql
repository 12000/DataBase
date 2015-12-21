create view top10routes as select first 10
shedule.name as name, place.train_id as train_id, count(place.train_id) as nums
from shedule, place
where shedule.train_id=place.train_id group by shedule.name, place.train_id
order by nums desc;
commit;



