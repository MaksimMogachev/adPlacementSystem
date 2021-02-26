package com.senlacourses.lecture3.electronicHotelAdministrator;

public class Main {

  public static void main(String[] args) {
    Administrator administrator = new Administrator("kakoi-to administrator");
    Hotel hotel = new Hotel(administrator);

    hotel.addNewRoom(1, 1, 2, 500);
    hotel.showAllRooms();
    hotel.addNewResident("kakoi-to zhilec", 2204014);
    hotel.addNewResident("drugoi zhilec", 2204015);
    hotel.addNewResident("esho odin zhilec", 2204016);
    hotel.showAllResidents();
    hotel.putInTheRoom(1, "kakoi-to zhilec", 2020, 4, 28, 5);
    hotel.putInTheRoom(1, "drugoi zhilec");

    hotel.showAllRooms();
    hotel.showOccupiedRooms();

    hotel.addNewService("pomit' pol", 1000);
    hotel.changeServicePrice("pomit' pol", 2000);
    hotel.showCurrentServices();

    hotel.addServiceToOccupiedRoom(1, "pomit' pol");
    hotel.showOccupiedRooms();

    hotel.changeRoomPrice(1, 800);
    hotel.evictFromTheRoom(1, 0);
    hotel.evictFromTheRoom(1);
    hotel.showOccupiedRooms();
    hotel.changeRoomCondition(1, RoomCondition.REPAIRED);
    hotel.showAllRooms();
  }
}