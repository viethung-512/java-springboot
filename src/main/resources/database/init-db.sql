create database ordermanagement;
use ordermanagement;

drop database if exists user;
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

insert into user (username, password, name, phone, email, role) values ('richard', '$2y$10$JYHnvp/ZTSw6ecY3zZfuzeErhMBxJjrLjBSojsJGFebkWd7u1kXRi', 'Richard', '0966382597', 'hung.ngo3@sotatek.com', 'ADMIN')