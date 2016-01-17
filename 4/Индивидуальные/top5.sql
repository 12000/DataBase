create view top5stations
as
select first 5 T1.st as st_name, T1.num + T2.num_start as summ from
(select ticket.stop_st as st, count(all ticket.stop_st) as num
from ticket
group by st order by num desc)T1,
(select ticket.start_st as st_start, count(all ticket.start_st) as num_start
from ticket
group by st_start order by num_start desc)T2
where T1.st = T2.st_start
order by summ desc;

commit;



