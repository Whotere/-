create table refresh_tokens(
    id varchar(255) primary key,
    token varchar(255) unique not null,
    expires_at timestamp not null,
    user_id varchar(255) references users (id) not null
);

--go