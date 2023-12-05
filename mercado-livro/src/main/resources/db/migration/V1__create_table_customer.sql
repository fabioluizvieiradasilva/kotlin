CREATE TABLE customer(
    id int auto_increment primary key,
    name varchar(50) not null,
    email varchar(50) not null unique
);