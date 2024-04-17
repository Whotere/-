create table planned_days(
    id varchar(255) primary key,
    name varchar(50) not null,
    user_id varchar(255) references users (id) not null
);

--go