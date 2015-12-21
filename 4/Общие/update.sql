create procedure udpdt (n char(255), s char(255)) as begin
update passenger set name = :n, surname = :s
where passenger.pas_id between 55 and 63;
end;

