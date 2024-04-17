create table users(
    id varchar(255) primary key,
    username varchar(20) unique not null,
    password varchar(255) not null
);

--go