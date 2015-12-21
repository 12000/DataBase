create view top5stations as select first 5
station.name, (count(ticket.start_st) + count(ticket.stop_st))/2 as nums
from station, ticket
where station.name=ticket.start_st or station.name=ticket.stop_st
group by station.name order by nums desc;
commit;



