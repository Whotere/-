create table meals(
    id varchar(255) primary key,
    name varchar(50) not null,
    description varchar(250),
    cooking_duration bigint not null,
    recipe varchar(10000),
    photo_id varchar(255) references photos (id),
    user_id varchar(255) references users (id) not null
);

--go