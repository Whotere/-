create table schedules(
    id varchar(255) primary key,
    next_repeat_after_days integer,
    start_date date not null,
    planned_day_id varchar(255) references planned_days (id) not null
);

--go