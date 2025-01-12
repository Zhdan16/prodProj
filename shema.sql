drop table if exists categories cascade;
drop table if exists products cascade;
drop table if exists orders cascade;
drop table if exists order_products cascade;
drop table if exists options cascade;
drop table if exists values cascade;
drop table if exists users cascade;
drop table if exists basket cascade;
drop table if exists review cascade;


create table users(
      id serial8,
      email varchar not null,
      password varchar not null,
      first_name varchar not null,
      last_name varchar not null,
      admin boolean,
      primary key (id),
      unique (email)
);
create table categories (
    id serial8,
    name varchar not null,
    primary key (id)
);

create table products(
    id serial8,
    category_id int8 not null,
    name varchar not null,
    price int4 not null,
    primary key (id)
);

create table orders(
    id serial8,
    status int2,
    user_id int8 not null,
    order_date date not null,
    address varchar not null,
    foreign key (user_id) references users(id),
    primary key (id)
);

create table order_products(
    id serial8,
    order_id int8 not null,
    product_id int8 not null,
    count int4 not null,
    primary key (id),
    foreign key (order_id) references orders(id),
    foreign key (product_id) references products(id)

);

create table options(
    id serial8,
    category_id int8 not null,
    name varchar not null,
    primary key (id),
    unique (category_id, name)
);

create table values(
   id serial8,
   product_id int8 not null,
   option_id int8 not null,
   value varchar not null,
   primary key (id),
   unique (product_id, option_id)
);



create table basket(
    id serial8,
    product_id int8 not null,
    user_id int8 not null,
    count int4 not null,
    order_index int4 not null,
    primary key (id),
    foreign key (product_id) references products(id),
    foreign key (user_id) references users(id),
    unique (id, user_id)
);

create table review(
    id serial8,
    user_id int8 not null,
    product_id int8 not null,
    rate int2 not null,
    text varchar(200) not null,
    published boolean,
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (product_id) references products(id),
    unique (user_id, product_id)
);





INSERT INTO categories (name) VALUES
    ('Смартфоны'),
    ('Ноутбуки'),
    ('Телевизоры'),
    ('Бытовая техника');
INSERT INTO products (category_id, name, price) VALUES
    (1, 'iPhone 13', 80000),
    (1, 'Samsung Galaxy S21', 70000),
    (1, 'Xiaomi Mi 11', 45000),
    (2, 'MacBook Air M1', 90000),
    (2, 'Dell XPS 13', 85000),
    (2, 'Lenovo ThinkPad X1', 70000),
    (3, 'Samsung 4K Smart TV', 60000),
    (3, 'LG OLED55', 95000),
    (4, 'Холодильник LG', 50000),
    (4, 'Стиральная машина Bosch', 40000);
INSERT INTO options (category_id, name) VALUES
    (1, 'Экран'),
    (1, 'Процессор'),
    (1, 'Камера'),
    (2, 'Процессор'),
    (2, 'Память'),
    (3, 'Диагональ экрана'),
    (4, 'Объем'),
    (4, 'Энергопотребление');
INSERT INTO values (product_id, option_id, value) VALUES
    -- iPhone 13
    (1, 1, '6.1 inch OLED'),
    (1, 2, 'A15 Bionic'),
    (1, 3, '12MP'),

    -- Samsung Galaxy S21
    (2, 1, '6.2 inch AMOLED'),
    (2, 2, 'Exynos 2100'),
    (2, 3, '64MP'),

    -- Xiaomi Mi 11
    (3, 1, '6.81 inch AMOLED'),
    (3, 2, 'Snapdragon 888'),
    (3, 3, '108MP'),

    -- MacBook Air M1
    (4, 4, 'Apple M1'),
    (4, 5, '8GB RAM'),

    -- Dell XPS 13
    (5, 4, 'Intel i7'),
    (5, 5, '16GB RAM'),

    -- Lenovo ThinkPad X1
    (6, 4, 'Intel i5'),
    (6, 5, '8GB RAM'),

    -- Samsung 4K Smart TV
    (7, 6, '55 inches'),

    -- LG OLED55
    (8, 6, '55 inches'),

    -- Холодильник LG
    (9, 7, '350 L'),
    (9, 8, 'A++'),

    -- Стиральная машина Bosch
    (10, 7, '7 kg'),
    (10, 8, 'A+');


