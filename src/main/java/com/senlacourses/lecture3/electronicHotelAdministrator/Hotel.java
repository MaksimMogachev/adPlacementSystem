package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hotel {

  private Administrator administrator;
  private final List<HotelRoom> listOfRooms = new ArrayList<>();
  private final List<HotelResident> listOfResidents = new ArrayList<>();
  private final List<Service> listOfServices = new ArrayList<>();
  private final List<CheckInRegistration> listOfOccupiedRooms = new ArrayList<>();

  public Hotel(Administrator administrator) {
    this.administrator = administrator;
  }

  public List<Service> getListOfServices() {
    return listOfServices;
  }

  public List<HotelRoom> getListOfRooms() {
    return listOfRooms;
  }

  public List<HotelResident> getListOfResidents() {
    return listOfResidents;
  }

  public void showAllResidents() {
    for (HotelResident hotelResident : listOfResidents) {
      System.out.println(hotelResident.toString());
    }
  }

  public void showAllRooms() {
    for (HotelRoom hotelRoom : listOfRooms) {
      System.out.println(hotelRoom.toString());
    }
  }

  public void showOccupiedRooms() {
    for (CheckInRegistration checkInRegistration : listOfOccupiedRooms) {
      System.out.println(checkInRegistration.toString());
    }
  }

  public void changeRoomPrice(int numberOfRoom, int newPrice) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (newPrice < 0) {
      throw new IllegalArgumentException("incorrect price");
    }

    listOfRooms.get(indexOfRoom).setPrice(newPrice);
  }

  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (findIndexOfCheckInRegistration(numberOfRoom) != -1) {
      throw new UnsupportedOperationException("the room must be vacated before changing the condition");
    }

    listOfRooms.get(indexOfRoom).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int numberOfRoom, String nameOfResident, int yearOfArrival, int month, int dayOfMonth, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(nameOfResident);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfOccupiedRoom == -1) {
      listOfOccupiedRooms.add(new CheckInRegistration(listOfRooms.get(indexOfRoom),
          listOfResidents.get(indexOfResident),
          LocalDate.of(yearOfArrival, month, dayOfMonth), daysOfStay));
      listOfRooms.get(indexOfRoom).setRoomIsOccupied(true);
    } else {
      throw new UnsupportedOperationException("the given room is occupied, use method with 2 arguments");
    }
  }

  public void putInTheRoom(int numberOfRoom, String nameOfResident) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(nameOfResident);

    if (indexOfOccupiedRoom == -1) {
      throw new UnsupportedOperationException("the given room is not occupied, use method with 6 arguments");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (listOfOccupiedRooms.get(indexOfOccupiedRoom).getHotelRoom().getRoomCapacity()
        > listOfOccupiedRooms.get(indexOfOccupiedRoom).getResidents().size()) {

      listOfOccupiedRooms.get(indexOfOccupiedRoom)
          .getResidents().add(listOfResidents.get(indexOfResident));
    } else {
      throw new UnsupportedOperationException("Maximum Size "
          + listOfRooms.get(indexOfRoom).getRoomCapacity() + " reached");
    }
  }

  public void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    if (indexOfOccupiedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (listOfOccupiedRooms.get(indexOfOccupiedRoom).getResidents().size() == 1) {
      evictFromTheRoom(numberOfRoom);
      return;
    }

    listOfOccupiedRooms.get(indexOfOccupiedRoom).getResidents()
        .remove(listOfOccupiedRooms.get(indexOfOccupiedRoom)
            .getResidents().get(indexOfResidentInRoom));

  }

  public void evictFromTheRoom(int numberOfRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfOccupiedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    listOfOccupiedRooms.remove(listOfOccupiedRooms.get(indexOfOccupiedRoom));
    listOfRooms.get(indexOfRoom).setRoomIsOccupied(false);
  }

  public void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    if (findIndexOfRoom(numberOfRoom) == -1) {
      listOfRooms.add(new HotelRoom(numberOfRoom, numberOfStars, roomCapacity, price));
    } else {
      throw new IllegalArgumentException("this room already exists");
    }
  }

  public void addNewResident(String fullName, int passportNumber) {
    listOfResidents.add(new HotelResident(fullName, passportNumber));
  }

  public void addNewService(String name, int price) {
    this.listOfServices.add(new Service(name, price));
  }

  public void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService) {
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfService = findIndexOfService(nameOfService);

    if (indexOfOccupiedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (indexOfService == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    listOfOccupiedRooms.get(indexOfOccupiedRoom)
        .getServices().add(listOfServices.get(indexOfService));
  }

  public void showCurrentServices() {
    for (Service service : listOfServices) {
      System.out.println(service.toString());
    }
  }

  public void changeServicePrice(String nameOfService, int newPrice) {
    int indexOfService = findIndexOfService(nameOfService);

    if (indexOfService == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    if (newPrice < 0) {
      throw new IllegalArgumentException("incorrect newPrice");
    }

    listOfServices.get(indexOfService).setPrice(newPrice);
  }

  private int findIndexOfRoom(int numberOfRoom) {
    int indexOfRoom = -1;
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getNumberOfRoom() == numberOfRoom) {
        indexOfRoom = i;
        break;
      }
    }
    return indexOfRoom;
  }

  private int findIndexOfService(String name) {
    int indexOfService = -1;
    for (int i = 0; i < listOfServices.size(); i++) {
      if (listOfServices.get(i).getName().equals(name)) {
        indexOfService = i;
        break;
      }
    }
    return indexOfService;
  }

  private int findIndexOfCheckInRegistration(int numberOfRoom) {
    int indexOfCheckInRegistration = -1;
    for (int i = 0; i < listOfOccupiedRooms.size(); i++) {
      if (listOfOccupiedRooms.get(i).getHotelRoom().getNumberOfRoom() == numberOfRoom) {
        indexOfCheckInRegistration = i;
        break;
      }
    }
    return indexOfCheckInRegistration;
  }
  private int findIndexOfResident(String name) {
    int indexOfResident = -1;
    for (int i = 0; i < listOfResidents.size(); i++) {
      if (listOfResidents.get(i).fullName().equals(name)) {
        indexOfResident = i;
        break;
      }
    }
    return indexOfResident;
  }

  public void sortByPrice() {
    listOfRooms.sort(Comparator.comparing(HotelRoom::getPrice));
  }
}
