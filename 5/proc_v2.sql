create procedure sr_zap(first_date date, second_date date)
returns( percent int)
as
declare variable rout_cnt int;
declare variable all_p int;
begin

select count(*) from shedule
where shedule.r_date between :first_date and :second_date
into :rout_cnt;

select SUM(p)/:rout_cnt from
(select T1.name1 as name1, T1.all_places as all_places,
T2.name as name2, T2.tick_cnt as tick_cnt, T2.tick_cnt*100/T1.all_places as p
from
(select shedule.route_id as name1, count(place.place_id) as all_places
from place, shedule
where shedule.train_id=place.train_id
group by name1 order by all_places desc)T1,

(select shedule.route_id as name, count(ticket.t_num) as tick_cnt
from shedule, ticket, place where
shedule.train_id=place.train_id and place.place_id=ticket.place_id
/*and ticket.r_date between '03.12.2015' and '31.12.2015' */
and ticket.r_date between :first_date and :second_date
group by name order by tick_cnt desc)T2
where T1.name1 = T2.name and T1.all_places > T2.tick_cnt order by p desc)
into :percent;

end;


