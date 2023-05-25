create table if not exists url
(
    id    serial primary key not null,
    url   varchar(2000),
    key   varchar(2000),
    count int
);