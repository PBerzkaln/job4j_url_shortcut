create table if not exists site
(
    id       serial primary key not null,
    name     varchar(2000),
    login    varchar(2000),
    password varchar(2000)
);