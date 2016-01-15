drop trigger aut_trig;
drop generator gen;

create generator gen;
set generator gen to 2147483306;

create trigger  aut_trig  for station
before insert
as begin
    NEW.st_id = GEN_ID(gen, 1);
end;



