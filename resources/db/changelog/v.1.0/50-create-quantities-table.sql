create table quantities(
    id varchar(255) primary key,
    amount double precision not null,
    measurement_unit_id varchar(255) references measurement_units (id) not null
);

--go