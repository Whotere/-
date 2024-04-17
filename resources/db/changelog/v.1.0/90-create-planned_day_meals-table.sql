create table planned_day_meals(
    id varchar(255) primary key,
    time time not null,
    meal_id varchar(255) references meals (id) not null,
    planned_day_id varchar(255) references planned_days (id) not null
);

--go