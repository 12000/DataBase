create view grouping as select  ticket.start_st as start_st,
count(ticket.start_st) as nums from ticket group by ticket.start_st;
commit;

