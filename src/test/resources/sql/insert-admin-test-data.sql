INSERT INTO aps.t_role
(id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO aps.profile
(id, phonenumber, rating, username) VALUES
(1, '123456', 0, 'second');

INSERT INTO aps.usr
(id, email, "password", username, profile_id) VALUES
(1, 'firstAdmin@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'firstAdmin', null),
(2, 'second@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'second', 1);

INSERT INTO aps.usr_t_role
(user_id, roles_id) VALUES
(1, 2),
(2, 1);

INSERT INTO aps.ad
(id, category, city, dateofcreation, description, isactive, ispaid, nameofad, price, profile_id) VALUES
(1, 'TRANSPORT', 'Orel', '2021-08-19', 'first description', true, false, 'first name', 100, 1);