create table measurement_units(
    id varchar(255) primary key,
    name varchar(10) unique not null
);

--go

insert into measurement_units (id, name)
    values ('54460fc7-01fa-4556-8500-278bda5d5a9f', 'гр'),
           ('6a67fc37-acc7-4b48-b7f5-cc2552981b26', 'мл'),
           ('0e2c7976-ffa5-4634-b6e9-c3383a966d19', 'шт');

--go