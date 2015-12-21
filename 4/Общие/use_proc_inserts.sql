execute procedure ins_passenger
(34, 'Sergey', 'Smirnov', 'passport', '9809999999');
execute procedure ins_station (100002, 'Tver');
execute procedure ins_train (74, 'E5K', 12);
execute procedure ins_place (78, 74, 2, 24, 0, 0);
execute procedure ins_ticket (88, 78, 'buy', 'Moscow', 'Tver');
execute procedure ins_shedule (177, 74, '654 Moscow-Tver', '27.12.2015');
execute procedure ins_r_station (177, 1, 'start', '-', 1);
execute procedure ins_t_order (7546, 88, 1);
execute procedure ins_orders (34, 7546);
commit;

