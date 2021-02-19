package com.senlacourses.lecture3.electronicHotelAdministrator;

public class Main {

  public static void main(String[] args) {
    Administrator administrator = new Administrator("kakoi-to administrator");

    administrator.getListOfRooms().addNewRoom(500, null);
//    administrator.getListOfRooms().putInTheRoom(0, "kakoi-to zhilec", 2204014);
    administrator.getListOfRooms().showCurrentList();

  }

}
