create table if not exists hero(
    id serial primary key,
    name text,
    attribute text,
    attackType text,
    damage int,
    movespeed int
);

create table if not exists item(
    id serial primary key,
    name text,
    cost int,
    secretShop int,
    sideShop int,
    recipe int
);