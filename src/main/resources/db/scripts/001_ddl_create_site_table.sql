create table if not exists site
(
    id       serial primary key not null,
    name     varchar(2000) unique,
    login    varchar(2000) unique,
    password varchar(2000)
);