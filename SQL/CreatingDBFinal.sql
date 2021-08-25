CREATE SCHEMA IF NOT EXISTS aps;

CREATE TABLE aps.chat (
	id bigserial NOT NULL,
	CONSTRAINT chat_pkey PRIMARY KEY (id)
);

CREATE TABLE aps.message (
	id bigserial NOT NULL,
	messagesendingtime timestamp NULL,
	messagetext varchar(255) NULL,
	sendername varchar(255) NULL,
	CONSTRAINT message_pkey PRIMARY KEY (id)
);

CREATE TABLE aps.profile (
	id bigserial NOT NULL,
	phonenumber varchar(255) NULL,
	rating int4 NOT NULL,
	username varchar(255) NULL,
	CONSTRAINT profile_pkey PRIMARY KEY (id),
	CONSTRAINT uk_3ipu5qkrkfqr9ju2ioua6qx9e UNIQUE (username)
);

CREATE TABLE aps.t_role (
	id int8 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT t_role_pkey PRIMARY KEY (id)
);

CREATE TABLE aps.ad (
	id bigserial NOT NULL,
	category varchar(255) NULL,
	city varchar(255) NULL,
	dateofcreation date NULL,
	description varchar(255) NULL,
	isactive bool NOT NULL,
	ispaid bool NOT NULL,
	nameofad varchar(255) NULL,
	price int4 NOT NULL,
	profile_id int8 NULL,
	CONSTRAINT ad_pkey PRIMARY KEY (id),
	CONSTRAINT fk926jrcb1dtvkxry757nggvasd FOREIGN KEY (profile_id) REFERENCES aps.profile(id)
);

CREATE TABLE aps.ad_comments (
	ad_id int8 NOT NULL,
	"comments" varchar(255) NULL,
	CONSTRAINT fk5sh2nn28j7ww2huaind904nyc FOREIGN KEY (ad_id) REFERENCES aps.ad(id)
);

CREATE TABLE aps.message_chat (
	chat_id int8 NULL,
	id int8 NOT NULL,
	CONSTRAINT message_chat_pkey PRIMARY KEY (id),
	CONSTRAINT fk55jfcx7a53kqg9t17mfyspd6x FOREIGN KEY (id) REFERENCES aps.message(id),
	CONSTRAINT fkialbt753y3cb7ivc6mwdlf8p7 FOREIGN KEY (chat_id) REFERENCES aps.chat(id)
);

CREATE TABLE aps.usr (
	id bigserial NOT NULL,
	email varchar(255) NULL UNIQUE,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	profile_id int8 NULL,
	CONSTRAINT uk_c52ii74ncaafi1oui3f2nl7v6 UNIQUE (username),
	CONSTRAINT usr_pkey PRIMARY KEY (id),
	CONSTRAINT fkiaf4v5f1j2trlpldhr79kbcev FOREIGN KEY (profile_id) REFERENCES aps.profile(id)
);

CREATE TABLE aps.usr_chat (
	users_id int8 NOT NULL,
	chats_id int8 NOT NULL,
	CONSTRAINT usr_chat_pkey PRIMARY KEY (users_id, chats_id),
	CONSTRAINT fk7c0g1wgbpmypp1ykrvijjtjdk FOREIGN KEY (chats_id) REFERENCES aps.chat(id),
	CONSTRAINT fktoelpspjbohx1k91dajpe5goj FOREIGN KEY (users_id) REFERENCES aps.usr(id)
);

CREATE TABLE aps.usr_t_role (
	user_id int8 NOT NULL,
	roles_id int8 NOT NULL,
	CONSTRAINT usr_t_role_pkey PRIMARY KEY (user_id, roles_id),
	CONSTRAINT fk27nvpx2fjoks19dd8q909ayvx FOREIGN KEY (user_id) REFERENCES aps.usr(id),
	CONSTRAINT fkkmlcuy8fb1mcmm850y2pr14dp FOREIGN KEY (roles_id) REFERENCES aps.t_role(id)
);

INSERT INTO aps.t_role(id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'); 
  