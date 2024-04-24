drop database if exists ordermanagement;
create database ordermanagement;
use ordermanagement;

drop table if exists user;
create table user (
                      id int primary key auto_increment,
                      username varchar(255),
                      password varchar(255),
                      name varchar(255),
                      phone varchar(255),
                      email varchar(255),
                      role varchar(255)
);

# password will be Richard123!

insert into user (username, password, name, phone, email, role) values ('richard', '$2y$10$JYHnvp/ZTSw6ecY3zZfuzeErhMBxJjrLjBSojsJGFebkWd7u1kXRi', 'Richard', '0966382597', 'hung.ngo3@sotatek.com', 'ADMIN');

drop table if exists customer;
create table customer (
                      id int primary key auto_increment,
                      name varchar(255),
                      phone varchar(255),
                      address varchar(255)
);

drop table if exists product;
create table product(
                        id int primary key auto_increment,
                        name varchar(255),
                        price double
);

drop table if exists inventory;
create table inventory(
                          id int primary key auto_increment,
                          product_id int NOT NULL ,
                          stock_quantity long,
                          updated_date date,
                          FOREIGN KEY (product_id) references product(id)
);

drop table if exists `order`;
create table `order`(
                        id int primary key auto_increment,
                        customer_id int not null,
                        total_money double not null,
                        issue_date date not null,
                        FOREIGN KEY (customer_id) references customer(id)
);

drop table if exists lineorder;
create table lineorder(
                          id int primary key auto_increment,
                          order_id int not null,
                          customer_id int not null,
                          product_id int not null,
                          quantity int not null,
                          FOREIGN KEY (order_id) references `order`(id),
                          FOREIGN KEY (customer_id) references customer(id),
                          FOREIGN KEY (product_id) references product(id)
);