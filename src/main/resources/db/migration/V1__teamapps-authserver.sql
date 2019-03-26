-- SCHEMA
create schema if not exists teamapps;

-- OAUTH client credentials
create table teamapps.oauth_client_details
(
  client_id               text not null
    constraint pk_oauth_client_details
      primary key,
  resource_ids            text,
  client_secret           text,
  scope                   text,
  authorized_grant_types  text,
  web_server_redirect_uri text,
  authorities             text,
  access_token_validity   text,
  refresh_token_validity  text,
  additional_information  text,
  autoapprove             text
);

-- INSERT teamapps client credentials
insert into teamapps.oauth_client_details
values ('teamapps', null, '{noop}teamapps_secret', 'teamapps',
        'authorization_code,refresh_token,password,client_credentials,implicit', 'http://localhost:3000?', 'ROLE_USER',
        36000, 36000, null, true);

-- USER id sequence
create sequence teamapps.user_id_seq
  minvalue 100
  cycle;

-- USER table
create table teamapps."user"
(
  id         integer default nextval('teamapps.user_id_seq' :: regclass) not null
    constraint pk_user
      primary key,
  email      varchar(255)                                                not null
    constraint user_email_idx_unique
      unique,
  login      varchar(255)                                                not null
    constraint user_login_idx_unique
      unique,
  first_name varchar(255)                                                not null,
  last_name  varchar(255)                                                not null,
  picture    bytea,
  password   varchar(100)                                                not null,
  roles      varchar(255)                                                not null
);

insert into teamapps.user(email, login, first_name, last_name, picture, password, roles)
values ('a.mihai@oodrive.com', 'a.mihai', 'Mihai', 'Alexandru', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('p.prihor@oodrive.com', 'p.prihor', 'Prihor', 'Paul', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('b.ungureanu@oodrive.com', 'b.ungureanu', 'Ungureanu', 'Bogdan', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('c.unguru@oodrive.com', 'c.unguru', 'Unguru', 'Claudiu', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('a.aioanei@oodrive.com', 'a.aioanei', 'Aioanei', 'Andre', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('m.gheorghe@oodrive.com', 'm.gheorghe', 'Gheorghe', 'Mihaela', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('a.parascuta@oodrive.com', 'a.parascuta', 'Parascuta', 'Adina', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('l.iordan@oodrive.com', 'l.iordan', 'Iordan', 'Laura', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('m.roman@oodrive.com', 'm.roman', 'Roman', 'Maria', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('v.moisuc@oodrive.com', 'v.moisuc', 'Moisuc', 'Vlad', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER'),
       ('b.prisecaru@oodrive.com', 'p.prisecaru', 'Prisecaru', 'Bogdan', null,
        '$2a$10$xjANLFsc5FZbzw3A8X34geZE4wQLG7U4jkReE4zwPd27vf5ss6uY.', 'ROLE_USER');



