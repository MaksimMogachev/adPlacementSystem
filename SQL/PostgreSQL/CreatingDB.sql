CREATE SCHEMA IF NOT EXISTS EHA ;

CREATE TABLE IF NOT EXISTS EHA.HotelResident (
  passportNumber INT NOT null UNIQUE,
  fullName VARCHAR(45) NOT NULL,
  PRIMARY KEY (passportNumber))
;


CREATE TABLE IF NOT EXISTS EHA.HotelRoom (
  numberOfRoom INT NOT null UNIQUE,
  numberOfStars INT NOT NULL,
  roomCapacity INT NOT NULL,
  price DECIMAL(15,2) NOT NULL,
  roomCondition VARCHAR(45) NULL,
  roomIsOccupied BOOLEAN NULL,
  PRIMARY KEY (numberOfRoom))
;


CREATE TABLE IF NOT EXISTS EHA.RegistrationCard (
  hotelRoom INT NOT NULL,
  checkInDate DATE NOT NULL,
  departureDate DATE NOT NULL,
  PRIMARY KEY (hotelRoom),
  CONSTRAINT fk_hotel_rooms_registration_cards1
    FOREIGN KEY (hotelRoom)
    REFERENCES EHA.HotelRoom (numberOfRoom)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;


CREATE TABLE IF NOT EXISTS EHA.Service (
  name VARCHAR(45) NOT null UNIQUE,
  price DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (name))
;


CREATE TABLE IF NOT EXISTS EHA.last_residents_rooms (
  residentInformation VARCHAR(300) NOT NULL,
  numberOfRoom INT NOT NULL,
  PRIMARY KEY (numberOfRoom),
  CONSTRAINT fk_residents_rooms_hotel_rooms
    FOREIGN KEY (numberOfRoom)
    REFERENCES EHA.HotelRoom (numberOfRoom)
    ON DELETE NO ACTION
    ON UPDATE NO action)
   ;
 
  CREATE INDEX IF NOT EXISTS fk_residents_rooms_hotel_rooms_idx ON EHA.last_residents_rooms (numberOfRoom ASC);
 

CREATE TABLE IF NOT EXISTS EHA.residents_cards (
   );


CREATE TABLE IF NOT EXISTS EHA.cards_services (
   );

