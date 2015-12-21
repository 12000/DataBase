create procedure inserts (i INTEGER, c char(255)) as begin
insert into station (st_id, name) values (:i, :c);
end;

