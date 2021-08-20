INSERT INTO aps.message
(id, messagesendingtime, messagetext, sendername) VALUES
(1, '2021-08-20 18:44:38.709', 'some message', 'first'),
(2, '2021-08-20 18:44:38.709', 'some message', 'second'),
(3, '2021-08-20 18:44:38.709', 'some message', 'first'),
(4, '2021-08-20 18:44:38.709', 'some message', 'second');

INSERT INTO aps.chat
(id) VALUES
(1);

INSERT INTO aps.message_chat
(chat_id, id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);

INSERT INTO aps.usr_chat
(users_id, chats_id) VALUES
(1, 1),
(2, 1);
