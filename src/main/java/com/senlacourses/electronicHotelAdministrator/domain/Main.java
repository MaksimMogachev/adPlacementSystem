package com.senlacourses.electronicHotelAdministrator.domain;

public class Main {

  public static void main(String[] args) {
    Administrator administrator = new Administrator("kakoi-to administrator");
    Hotel hotel = new Hotel(administrator);

    hotel.addNewRoom(1, 6, 2, 500);
    hotel.addNewRoom(2, 8, 4, 400);
    hotel.addNewRoom(3, 2, 6, 300);
    hotel.addNewRoom(4, 3, 3, 800);
    hotel.showAllRooms();
    System.out.println();
    hotel.showAllRoomsByCriterion(RoomSortingCriteria.PRICE);
    System.out.println();
    hotel.showAllRoomsByCriterion(RoomSortingCriteria.ROOM_CAPACITY);
    System.out.println();
    hotel.showAllRoomsByCriterion(RoomSortingCriteria.NUMBER_OF_STARS);
    System.out.println();

    hotel.addNewResident("kakoi-to zhilec", 2204014);
    hotel.addNewResident("drugoi zhilec", 2204015);
    hotel.addNewResident("esho odin zhilec", 2204016);

    hotel.putInTheRoom(1, "kakoi-to zhilec", 2021, 3, 1, 5);

    hotel.showFreeRoomsByCriterion(RoomSortingCriteria.PRICE);
    System.out.println();
    hotel.showFreeRoomsByCriterion(RoomSortingCriteria.ROOM_CAPACITY);
    System.out.println();
    hotel.showFreeRoomsByCriterion(RoomSortingCriteria.NUMBER_OF_STARS);
    System.out.println();

    hotel.putInTheRoom(2, "drugoi zhilec", 2021, 3, 1, 5);
    hotel.putInTheRoom(3, "esho odin zhilec", 2021, 3, 1, 5);
    hotel.showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria.ROOM_RELEASE_DATE);
    System.out.println();
    hotel.showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria.ALPHABETICALLY);
    hotel.showNumberOfFreeRooms();
    hotel.showNumberOfCurrentResidents();

//    hotel.showAllRooms();
//    hotel.showOccupiedRooms();
//
//    hotel.addNewService("pomit' pol", 1000);
//    hotel.changeServicePrice("pomit' pol", 2000);
//    hotel.showCurrentServices();
//
//    hotel.addServiceToBookedRoom(1, "pomit' pol");
//    hotel.showOccupiedRooms();
//
//    hotel.changeRoomPrice(1, 800);
//    hotel.evictFromTheRoom(1, 0);
//    hotel.evictFromTheRoom(1);
//    hotel.showOccupiedRooms();
//    hotel.changeRoomCondition(1, RoomCondition.REPAIRED);
//    hotel.showAllRooms();
  }
}