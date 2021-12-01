create table favorites
(
    id        int auto_increment
        primary key,
    name      mediumtext null,
    phone     mediumtext null,
    address_1 mediumtext null,
    zip_code  int        null,
    state     char(2)    null,
    distance  double     null,
    rating    double     null,
    user_id   int        null,
    constraint favorites_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);

INSERT INTO test_LocalLinks.favorites (name, phone, address_1, zip_code, state, distance, rating, user_id) VALUES ('Top Golf', '2383787492', '543 Church rd', 58772, 'WI', 12.3, 4.6, 1);
INSERT INTO test_LocalLinks.favorites (name, phone, address_1, zip_code, state, distance, rating, user_id) VALUES ('Hickory Grove Golf Course', '2383787492', '943 Seminal dr', 58743, 'WI', 32.5, 3.1, 1);
INSERT INTO test_LocalLinks.favorites (name, phone, address_1, zip_code, state, distance, rating, user_id) VALUES ('Hickory Grove Golf Course', '2383787492', '943 Seminal dr', 58743, 'WI', 32.5, 3.9, 2);
INSERT INTO test_LocalLinks.favorites (name, phone, address_1, zip_code, state, distance, rating, user_id) VALUES ('River Valley Country Club', '2383787492', '1112 Hwy 60', 56748, 'WI', 49.9, 2.5, 1);