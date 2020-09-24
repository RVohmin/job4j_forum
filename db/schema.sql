drop table if exists authorities;
CREATE TABLE if not exists authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);
insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

drop table if exists users;
CREATE TABLE if not exists users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(200) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

drop table if exists topics;
create table if not exists topics
(
    id        serial primary key,
    name      varchar(200),
    status    integer,
    user_id int references users (id)
);

drop table if exists posts;
create table if not exists posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    user_id int references users(id),
    topic_id int references topics(id)
);