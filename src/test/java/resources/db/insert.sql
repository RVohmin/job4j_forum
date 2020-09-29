insert into authorities (authority)
values ('ROLE_ADMIN');
insert into authorities (authority)
values ('ROLE_USER');

insert into users (username, password, authority_id)
values ('root', '$2a$10$mz/VdWBUL1cCFDfX2ipqXegjNunD3Zp2p272jiMDh/TW.ks8LTjmS',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into topics (name, user_id) values ('О чем этот форум?', 1);
insert into topics (name, user_id) values ('Правила форума.', 1);

insert into posts (name, description, user_id, topic_id) values ('test post', 'test description',
                                                                 1, 1);
insert into posts (name, description, user_id, topic_id) values ('test post', 'test description', 1, 2);
