create table products(
    id varchar(255) primary key,
    name varchar(50) not null,
    price double precision not null,
    producer varchar(50),
    photo_id varchar(255) references photos (id),
    product_quantity_id varchar(255) references quantities (id) not null,
    user_id varchar(255) references users (id) not null
);

--go