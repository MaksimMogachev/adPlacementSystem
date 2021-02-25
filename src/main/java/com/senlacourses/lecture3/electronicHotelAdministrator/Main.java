package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.Calendar;

public class Main {

  public static void main(String[] args) {
    Administrator administrator = new Administrator("kakoi-to administrator");
    Hotel hotel = new Hotel(administrator);

    hotel.addNewRoom(1, 1, 2, 500);
    hotel.showAllRooms();
    hotel.addNewResident("kakoi-to zhilec", 2204014);
    hotel.showAllResidents();
    hotel.putInTheRoom(1, 0, 2020, Calendar.APRIL, 28, 5);
    hotel.showAllRooms();
    hotel.showOccupiedRooms();

    hotel.addNewService("pomit' pol", 1000);
    hotel.changeServicePrice("pomit' pol", 2000);
    hotel.showCurrentServices();

    hotel.addServiceToOccupiedRoom(1, "pomit' pol");
    hotel.showOccupiedRooms();

    hotel.changeRoomPrice(1, 800);
    hotel.evictFromTheRoom(1, 0);
    hotel.changeRoomCondition(1, RoomCondition.REPAIRED);
    hotel.showAllRooms();

  }
}