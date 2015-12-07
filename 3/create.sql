create database 'D:\univer\4\BD\1\ticket_base.fdb' user 'SYSDBA' password 'masterkey';
commit;

connect 'D:\univer\4\BD\1\ticket_base.fdb' user 'SYSDBA' password 'masterkey';
create table train(train_id INT PRIMARY KEY,
            type char(255) NOT NULL,
            carriages INT NOT NULL);


create table station(st_id INT PRIMARY KEY,
            name char(255) NOT NULL);
create table passenger(pas_id INT PRIMARY KEY,
            name char(255) NOT NULL,
            surname char(255) NOT NULL,
            document INT NOT NULL,
            doc_num INT NOT NULL);
alter table passenger
alter column document type char(255);

create table place(place_id INT PRIMARY KEY,
            train_id INT REFERENCES train(train_id) NOT NULL);
alter table place add num_in_car INT NOT NULL;
alter table place add car_type char(255) NOT NULL;
alter table place add pl_pos char(255) NOT NULL;
alter table ticket add start_st char(255) NOT NULL;
alter table ticket add stop_st char(255) NOT NULL;

create table ticket(t_num INT NOT NULL,
            place_id INT REFERENCES place(place_id) NOT NULL,
            pas_id INT REFERENCES passenger(pas_id) NOT NULL,
            status char(255) NOT NULL,
start_st char(255),
                    stop_st char(255));

create table shedule(route_id INT PRIMARY KEY,
            train_id INT REFERENCES train(train_id) NOT NULL,
            name char(255) NOT NULL,
            r_date char(255) NOT NULL);
create table route_station(route_id INT REFERENCES shedule(route_id) NOT NULL,
            st_id INT REFERENCES station(st_id) NOT NULL,
            st_func NOT NULL,
            stop_time char(255) NOT NULL,
num_in_route INT NOT NULL);
commit;

