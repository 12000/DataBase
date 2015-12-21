create procedure del1 (n char(255)) as begin
delete from passenger where name= :n and pas_id=(select MIN(pas_id)
from passenger where name= :n);
end;

