create database IF NOT EXISTS  `example_db`;

create table example_car
(
    id            bigint auto_increment
        primary key,
    uuid          varchar(36) not null,
    name          varchar(100) null,
    price         float null,
    id_owner      bigint null,
    created_date  timestamp null,
    modified_date timestamp null,
    constraint example_car_uuid_unique
        unique (uuid)
);

create table example_user
(
    id            bigint auto_increment
        primary key,
    uuid          varchar(36) not null,
    name          varchar(300) null,
    surname       varchar(300) null,
    email         varchar(300) null,
    cash          float  null,
    created_date  timestamp null,
    modified_date timestamp null,
    constraint example_car_uuid_unique
        unique (uuid)
);

create table example_order
(
    id            bigint auto_increment
        primary key,
    uuid          varchar(36) not null,
    id_user       bigint null,
    id_car        bigint null,
    price         float  null,
    created_date  timestamp null,
    modified_date timestamp null,
    constraint example_car_uuid_unique
        unique (uuid),
    constraint example_user_to_order_fk
        foreign key (id_user) references example_user (id),
    constraint example_car_to_order_fk
        foreign key (id_car) references example_car (id)
);


create table example_car_history
(
    id            bigint auto_increment,
    uuid          varchar(36) not null,
    name          varchar(100) null,
    price         float  null,
    id_owner      bigint null,
    created_date  timestamp null,
    modified_date timestamp null,
    REVTYPE                tinyint,
    REV                    integer not null,
    REVEND INTEGER ,
    primary key (id, REV)
);

create table example_user_history
(
    id            bigint auto_increment,
    uuid          varchar(36) not null,
    name          varchar(300) null,
    surname       varchar(300) null,
    email         varchar(300) null,
    cash          float  null,
    created_date  timestamp null,
    modified_date timestamp null,
    REVTYPE                tinyint,
    REV                    integer not null,
    REVEND INTEGER ,
    primary key (id, REV)
);

create table example_order_history
(
    id            bigint auto_increment,
    uuid          varchar(36) not null,
    id_user       bigint null,
    id_car        bigint null,
    price         float  null,
    created_date  timestamp null,
    modified_date timestamp null,
    REVTYPE                tinyint,
    REV                    integer not null,
    REVEND INTEGER ,
    primary key (id, REV)
);

create table `example_db`.REVINFO
(
    REV int auto_increment
        primary key,
    username varchar(400) null,
    REVTSTMP bigint null
);