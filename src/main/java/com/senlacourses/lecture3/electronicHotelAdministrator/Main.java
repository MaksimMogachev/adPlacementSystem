package com.senlacourses.lecture3.electronicHotelAdministrator;

public class Main {

  public static void main(String[] args) {
    Administrator administrator = new Administrator("kakoi-to administrator");

    administrator.getListOfRooms().addNewRoom(1, 500, null);
    administrator.getListOfRooms().putInTheRoom(1, "kakoi-to zhilec", 2204014);
    administrator.getListOfRooms().showCurrentRooms();

    administrator.getListOfRooms().changePrice(1, 800);
    administrator.getListOfRooms().evictFromTheRoom(1, 0);
    administrator.getListOfRooms().changeRoomCondition(1, RoomCondition.REPAIRED);
    administrator.getListOfRooms().showCurrentRooms();

    administrator.getServices().addNewService("pomit' pol", 1000);
    administrator.getServices().changePrice("pomit' pol", 2000);
    administrator.getServices().showCurrentServices();

  }
}