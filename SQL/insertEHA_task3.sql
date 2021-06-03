INSERT hotel_rooms(number_of_room, number_of_stars, room_capacity, price)
VALUES
(1,2,2,500),
(2,2,4,228),
(3,3,2,322),
(4,3,5,777),
(5,2,3,500);

INSERT hotel_residents(full_name, passport_number)
VALUES 
('Иванов Иван Иванович',1121),
('Петров Петр Петрович',1232),
('Сергеев Сергей Сергеевич',1233),
('Акакиев Акакий Акакиевич',1260),
('Васильев Василий Василиевич',1288),
('Александров Александр Александрович',2112);

INSERT registration_cards(hotel_room, residents, check_in_date, departure_date)
VALUES
(1, 'Иванов Иван Иванович', '2021-05-29', '2021-05-31'),
(2, 'Акакиев Акакий Акакиевич', '2021-06-21', '2021-06-30'),
(3, 'Сергеев Сергей Сергеевич', '2021-05-25', '2021-05-30');