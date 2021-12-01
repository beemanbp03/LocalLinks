create table user
(
    id         int auto_increment
        primary key,
    user_name  varchar(20) not null,
    password   varchar(25) not null,
    email      varchar(50) not null,
    first_name varchar(25) not null,
    last_name  varchar(40) not null,
    zip_code   int         not null
);

INSERT INTO test_LocalLinks.user (user_name, password, email, first_name, last_name, zip_code) VALUES ('beemanbp03', 'student', 'bpbeeman@madisoncollege.edu', 'Boulder', 'Beeman', 53704);
INSERT INTO test_LocalLinks.user (user_name, password, email, first_name, last_name, zip_code) VALUES ('dexter04', 'student', 'dexter@madisoncollege.edu', 'Dexter', 'Beeman', 53805);
INSERT INTO test_LocalLinks.user (user_name, password, email, first_name, last_name, zip_code) VALUES ('JohnDoe117', 'student', 'johndoe@madisoncollege.edu', 'John', 'Doe', 53929);