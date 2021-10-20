[<<<Back](../README.md)
# Database Design
The database **LocalLinks** will feature two tables called **user** and **favorites**.  Both tables will share a 
**one-to-many relationship** with the user being associated with multiple favorites
## mySQL Script
```create table user
(
id int auto_increment
primary key,
user_name varchar(20) not null,
password varchar(25) not null,
email varchar(50) not null,
first_name varchar(25) not null,
last_name varchar(40) not null,
zip_code int not null
);

create table favorites
(
id int auto_increment
primary key,
name mediumtext not null,
phone mediumtext null,
address_1 mediumtext not null,
zip_code int null,
state char(2) null,
distance double null,
rating double null,
user_id int null,
constraint favorites_user_id_fk
foreign key (user_id) references user (id)
on update cascade on delete cascade
);

```

