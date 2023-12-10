create database activity_module;

create table devices
(
    device_id     varchar(255) not null
        primary key,
    type          varchar(255) null,
    os_platform   varchar(255) null,
    browser       varchar(255) null,
    web_app       varchar(255) null,
    creation_date varchar(255) null
)
    collate = utf8mb4_unicode_ci;

create table locations
(
    id            int auto_increment
        primary key,
    country       varchar(255) null,
    city          varchar(255) null,
    creation_date varchar(255) null
)
    collate = utf8mb4_vietnamese_ci;

create table users
(
    id            int auto_increment
        primary key,
    user_id       varchar(255) not null,
    device_id     varchar(255) not null,
    web_app       varchar(255) null,
    creation_date varchar(255) null,
    constraint user_id
        unique (user_id),
    constraint fk_device_id_u
        foreign key (device_id) references devices (device_id)
)
    collate = utf8mb4_unicode_ci;

create table visits
(
    id            int auto_increment
        primary key,
    user_id       varchar(255) default '0'  not null,
    device_id     varchar(255) default 'no' not null,
    location_id   int          default 0    not null,
    web_app       varchar(255)              null,
    page          varchar(255)              null,
    visit_time    double                    null,
    target_action int          default 0    null,
    creation_date varchar(255)              null,
    constraint fk_device_id
        foreign key (device_id) references devices (device_id),
    constraint fk_location_id
        foreign key (location_id) references locations (id),
    constraint fk_user_id
        foreign key (user_id) references users (user_id)
)
    collate = utf8mb4_unicode_ci;
