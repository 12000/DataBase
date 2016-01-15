/*drop exception tick_control;
drop trigger add_tick;*/

create exception tick_control 'This ticket already using!';

create trigger add_tick for t_order
before insert
as begin
    if(new.t_num in (select t_order.t_num from t_order
        where new.t_num = t_order.t_num))
    then
        exception tick_control;
        
end

