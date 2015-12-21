create procedure del2 as begin
delete from passenger where pas_id
not in (select pas_id from orders);
end;

