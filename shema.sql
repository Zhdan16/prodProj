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

INSERT INTO users (email, password, first_name, last_name)
VALUES
    ('user1@example.com', 'password1', 'John', 'Doe'),
    ('user2@example.com', 'password2', 'Jane', 'Smith'),
    ('user3@example.com', 'password3', 'Bob', 'Johnson'),
    ('user4@example.com', 'password4', 'Alice', 'Williams'),
    ('user5@example.com', 'password5', 'Mike', 'Brown'),
    ('user6@example.com', 'password6', 'Emily', 'Davis'),
    ('user7@example.com', 'password7', 'Sarah', 'Miller'),
    ('user8@example.com', 'password8', 'Tom', 'Wilson'),
    ('user9@example.com', 'password9', 'Lily', 'Moore'),
    ('user10@example.com', 'password10', 'James', 'Taylor');

-- Insert 10 categories
INSERT INTO categories (name)
VALUES
    ('Electronics'),
    ('Fashion'),
    ('Home'),
    ('Beauty'),
    ('Sports'),
    ('Toys'),
    ('Books'),
    ('Music'),
    ('Movies'),
    ('Games');

-- Insert 10 products
INSERT INTO products (category_id, name, price)
VALUES
    (1, 'Smartphone', 500),
    (1, 'Laptop', 1000),
    (2, 'T-Shirt', 20),
    (2, 'Jeans', 50),
    (3, 'Sofa', 500),
    (3, 'Table', 100),
    (4, 'Shampoo', 10),
    (4, 'Conditioner', 15),
    (5, 'Football', 20),
    (5, 'Basketball', 30);

-- Insert 10 orders
-- INSERT INTO orders (status, user_id, order_date, address)
-- VALUES
--     (1, 1, '2023-02-01', '123 Main St'),
--     (2, 2, '2023-02-02', '456 Oak Ave'),
--     (1, 3, '2023-02-03', '789 Pine Dr'),
--     (2, 4, '2023-02-04', '101 Maple Blvd'),
--     (1, 5, '2023-02-05', '202 Birch St'),
--     (2, 6, '2023-02-06', '303 Cedar Rd'),
--     (1, 7, '2023-02-07', '404 Elm St'),
--     (2, 8, '2023-02-08', '505 Spruce Ln'),
--     (1, 9, '2023-02-09', '606 Willow St'),
--     (2, 10, '2023-02-10', '707 Aspen Dr');

-- Insert 10 order products
-- INSERT INTO order_products (order_id, product_id, count)
-- VALUES
--     (1, 1, 1),
--     (1, 2, 1),
--     (2, 3, 1),
--     (2, 4, 1),
--     (3, 5, 1),
--     (3, 6, 1),
--     (4, 7, 1),
--     (4, 8, 1),
--     (5, 9, 1),
--     (5, 10, 1);

-- Insert 10 options
INSERT INTO options (category_id, name)
VALUES
    (1, 'Color'),
    (1, 'Memory'),
    (2, 'Size'),
    (2, 'Material'),
    (3, 'Shape'),
    (3, 'Material'),
    (4, 'Type'),
    (4, 'Brand'),
    (5, 'Size'),
    (5, 'Weight');

-- Insert 10 values
INSERT INTO values (product_id, option_id, value)
VALUES
    (1, 1, 'Black'),
    (1, 2, '16GB'),
    (2, 1, 'White'),
    (2, 2, '32GB'),
    (3, 3, 'Large'),
    (3, 4, 'Cotton'),
    (4, 5, 'Round'),
    (4, 6, 'Wood'),
    (5, 7, 'Shampoo'),
    (5, 8, 'Pantene');

-- Insert 10 basket entries
INSERT INTO basket (product_id, user_id, count)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 1),
    (5, 5, 1),
    (6, 6, 1),
    (7, 7, 1),
    (8, 8, 1),
    (9, 9, 1),
    (10, 10, 1);

INSERT INTO review (user_id, product_id, rate, text, published)
VALUES
    (1, 1, 5, 'Great product!', true),
    (2, 2, 4, 'Good, but not perfect', true),
    (3, 3, 5, 'Love it!', true),
    (4, 4, 3, 'Okay, but not impressed', true),
    (5, 5, 5, 'Best product ever!', true),
    (6, 6, 4, 'Good value for money', true),
    (7, 7, 5, 'Excellent product!', true),
    (8, 8, 3, 'Not bad, but not great', true),
    (9, 9, 5, 'Fantastic product!', true),
    (10, 10, 4, 'Good, but not perfect', true);