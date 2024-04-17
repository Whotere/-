create table ingredients(
    id varchar(255) primary key,
    name varchar(50) not null,
    meal_id varchar(255) references meals (id) not null,
    product_id varchar(255) references products (id) not null,
    product_quantity_id varchar(255) references quantities (id) not null
);

--go