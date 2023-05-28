create table if not exists url
(
    id      serial primary key not null,
    url     varchar(2000) unique,
    key     varchar(2000) unique,
    count   int,
    site_id int references site (id)
);