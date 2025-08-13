-- users.ddl 검색하면 나옴, _ignore 는 지워야함
create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

-- user 데이터
insert into users
values ('user', '{noop}User!!@1234', '1');
insert into authorities
values ('user', 'read');

insert into users
values ('admin', '{bcrypt}$2a$12$uFMRFsj3qUPPTHoa2jXICehC8kizIXSKXtVNv9rUbV6rVX.rl1hxC', '1');
insert into authorities
values ('admin', 'admin');


-- customer

create table customer
(
    id    bigint       not null auto_increment,
    email varchar(45)  not null,
    pwd   varchar(200) not null,
    role  varchar(45)  not null,
    primary key (id)
);

insert into customer(email, pwd, role)
values ('happy@example.com',
        '{noop}EazyBytes@12345',
        'read');
insert into customer(email, pwd, role)
values ('admin@example.com',
        '{bcrypt}$2a$12$uFMRFsj3qUPPTHoa2jXICehC8kizIXSKXtVNv9rUbV6rVX.rl1hxC',
        'admin');