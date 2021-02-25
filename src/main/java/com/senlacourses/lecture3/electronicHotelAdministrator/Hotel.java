package com.senlacourses.lecture3.electronicHotelAdministrator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
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
    int index = findIndexOfRoom(numberOfRoom);
    listOfRooms.get(index).setPrice(newPrice);
  }

  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    int index = findIndexOfRoom(numberOfRoom);
    listOfRooms.get(index).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int numberOfRoom, int indexOfResident, int yearOfArrival, int month, int dayOfMonth, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);

    if (indexOfOccupiedRoom == -1) {
      listOfOccupiedRooms.add(new CheckInRegistration(listOfRooms.get(indexOfRoom),
          listOfResidents.get(indexOfResident),
          new GregorianCalendar(yearOfArrival, month, dayOfMonth), daysOfStay));
      listOfRooms.get(indexOfRoom).setRoomIsOccupied(true);
    } else {
      throw new UnsupportedOperationException("the given room is occupied, use method with 2 arguments");
    }
  }

  public void putInTheRoom(int numberOfRoom, int indexOfResident) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);

    if (indexOfOccupiedRoom == -1) {
      throw new UnsupportedOperationException("the given room is not occupied, use method with 6 arguments");
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
    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
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

    if (indexOfOccupiedRoom == -1 || indexOfService == -1) {
      throw new IllegalArgumentException("incorrect arguments");
    }

    listOfOccupiedRooms.get(indexOfOccupiedRoom)
        .getServices().add(listOfServices.get(indexOfService));
  }

  public void showCurrentServices() {
    for (Service service : listOfServices) {
      System.out.println(service.toString());
    }
  }

  public void changeServicePrice(String nameOfService, int price) {
    int index = findIndexOfService(nameOfService);

    if (index == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    listOfServices.get(index).setPrice(price);
  }

  private int findIndexOfRoom(int numberOfRoom) {
    int index = -1;
    for (int i = 0; i < listOfRooms.size(); i++) {
      if (listOfRooms.get(i).getNumberOfRoom() == numberOfRoom) {
        index = i;
        break;
      }
    }
    return index;
  }

  private int findIndexOfService(String name) {
    int index = -1;
    for (int i = 0; i < listOfServices.size(); i++) {
      if (listOfServices.get(i).getName().equals(name)) {
        index = i;
        break;
      }
    }
    return index;
  }

  private int findIndexOfCheckInRegistration(int numberOfRoom) {
    int index = -1;
    for (int i = 0; i < listOfOccupiedRooms.size(); i++) {
      if (listOfOccupiedRooms.get(i).getHotelRoom().getNumberOfRoom() == numberOfRoom) {
        index = i;
        break;
      }
    }
    return index;
  }

  public void sortByPrice() {
    listOfRooms.sort(Comparator.comparing(HotelRoom::getPrice));
  }
}
