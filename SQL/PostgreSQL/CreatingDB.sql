create schema if not exists EHA ;

create table if not exists EHA.HotelResident ( 
passportNumber INT not null unique,
fullName VARCHAR(45) not null,
primary key (passportNumber)) ;

create table if not exists EHA.Usr ( 
id BIGINT not null unique,
username VARCHAR(45) not null unique ,
password VARCHAR(45) not null,
primary key (id)) ;

create table if not exists EHA.t_role ( 
id bigint not null unique,
name varchar(45) not null unique,
primary key (id)) ;

create table if not exists EHA.usr_t_role ( 
users_id bigint not null,
roles_id bigint not null,
primary key (users_id, roles_id),
constraint fk_roles_id_t_role foreign key (roles_id) references eha.t_role on
delete
	cascade on
	update
		cascade,
		constraint fk_users_id_usr foreign key (users_id) references eha.usr on
		delete
			cascade on
			update
				cascade) ;
	
INSERT INTO EHA.t_role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'); 

create table if not exists EHA.HotelRoom ( 
numberOfRoom INT not null unique,
numberOfStars INT not null,
roomCapacity INT not null,
price DECIMAL(15,
2) not null,
roomCondition VARCHAR(45) null,
roomIsOccupied BOOLEAN null,
primary key (numberOfRoom)) ;

create table if not exists EHA.RegistrationCard ( 
hotelRoom INT not null,
checkInDate DATE not null,
departureDate DATE not null,
primary key (hotelRoom),
constraint fk_hotel_rooms_registration_cards1 foreign key (hotelRoom) references EHA.HotelRoom (numberOfRoom) on
delete
	cascade on
	update
		cascade) ;

create table if not exists EHA.Service ( 
name VARCHAR(45) not null unique,
price DECIMAL(15,2) not null,
primary key (name)) ;

create table if not exists EHA.last_residents_rooms ( 
residentInformation VARCHAR(300) not null,
numberOfRoom INT not null,
primary key (numberOfRoom),
constraint fk_residents_rooms_hotel_rooms foreign key (numberOfRoom) references EHA.HotelRoom (numberOfRoom) on
delete
	no action on
	update
		no action) ;

create index if not exists fk_residents_rooms_hotel_rooms_idx on
EHA.last_residents_rooms (numberOfRoom asc);

create table if not exists EHA.residents_cards ( );

create table if not exists EHA.cards_services ( );
