create procedure ins_station (i INTEGER, n char(255)) as begin
insert into station (st_id, name) values (:i, :n);
end;

create procedure ins_passenger (i INTEGER, n char(255), s char(255), d char(255),
num char(255)) as begin
insert into passenger (pas_id, name, surname, document, doc_num)
values (:i, :n, :s, :d, :num);
end;

create procedure ins_train (i INTEGER, t char(255), c INTEGER) as begin
insert into train (train_id, "TYPE", carriages) values (:i, :t, :c);
end;

create procedure ins_place (i INTEGER, ti integer, c integer,
num INTEGER, ct integer, pt integer) as begin
insert into place (place_id, train_id, carriage, num_in_car, car_type_id,
place_type_id) values (:i, :ti, :c, :num, :ct, :pt);
end;

create procedure ins_ticket (tn INTEGER, i integer, status char(255),
sta char(255), sto char(255)) as begin
insert into ticket (t_num, place_id, status, start_st, stop_st)
values (:tn, :i, :status, :sta, :sto);
end;

create procedure ins_shedule (ri INTEGER, ti integer, n char(255),rd char(255))
as begin
insert into shedule (route_id, train_id, name, r_date)
values (:ri, :ti, :n, :rd);
end;

create procedure ins_r_station (ri INTEGER, si integer, f char(255),
stime char(255), num integer)
as begin
insert into route_station (route_id, st_id, st_func, stop_time, num_in_route)
values (:ri, :si, :f, :stime, :num);
end;

create procedure ins_t_order (oi INTEGER, tnum integer, tsnum integer)
as begin
insert into t_order (order_id, t_num, tickets_num)
values (:oi, :tnum, :tsnum);
end;

create procedure ins_orders (i INTEGER, oi integer)
as begin
insert into orders (pas_id, order_id)
values (:i, :oi);
end;



