INSERT INTO aps.t_role
(id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO aps.profile
(id, phonenumber, rating, username) VALUES
(1, '123456', 0, 'first'),
(2, '123456', 0, 'second'),
(3, '123456', 2, 'third');

INSERT INTO aps.usr
(id, email, "password", username, profile_id) VALUES
(1, 'first@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'first', 1),
(2, 'second@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'second', 2),
(3, 'third@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'third', 3),
(4, 'fourth@mail.ru', '$2a$10$3NOkh.r78b8SvckXutwM0.Hp4YgENuNYbfwXSgJ2mHrq6d7WV2II6', 'fourth', null);

INSERT INTO aps.usr_t_role
(user_id, roles_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);