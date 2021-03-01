package com.senlacourses.electronicHotelAdministrator.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hotel {

  private Administrator administrator;
  private List<HotelRoom> allRooms = new ArrayList<>();
  private List<HotelResident> residents = new ArrayList<>();
  private List<Service> services = new ArrayList<>();
  private List<CheckInRegistration> bookedRooms = new ArrayList<>();

  public Hotel(Administrator administrator) {
    this.administrator = administrator;
  }

  public List<Service> getServices() {
    return services;
  }

  public List<HotelRoom> getAllRooms() {
    return allRooms;
  }

  public List<HotelResident> getResidents() {
    return residents;
  }

  public List<CheckInRegistration> getBookedRooms() {
    return bookedRooms;
  }

  public void showAllResidents() {
    for (HotelResident hotelResident : residents) {
      System.out.println(hotelResident.toString());
    }
  }

  public void showAllRooms() {
    for (HotelRoom hotelRoom : allRooms) {
      System.out.println(hotelRoom.toString());
    }
  }

  public void showOccupiedRooms() {
    for (CheckInRegistration checkInRegistration : bookedRooms) {
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

    allRooms.get(indexOfRoom).setPrice(newPrice);
  }

  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (findIndexOfCheckInRegistration(numberOfRoom) != -1) {
      throw new UnsupportedOperationException(
          "the room must be vacated before changing the condition");
    }

    allRooms.get(indexOfRoom).setRoomCondition(roomCondition);
  }

  public void putInTheRoom(int numberOfRoom, String fullNameOfResident, int yearOfArrival, int month,
      int dayOfMonth, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfBookedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfBookedRoom == -1) {
      bookedRooms.add(new CheckInRegistration(allRooms.get(indexOfRoom),
          residents.get(indexOfResident),
          LocalDate.of(yearOfArrival, month, dayOfMonth), daysOfStay));

      if (LocalDate.of(yearOfArrival, month, dayOfMonth).equals(LocalDate.now())) {
        allRooms.get(indexOfRoom).setRoomIsOccupied(true);
      }
    } else {
      throw new UnsupportedOperationException(
          "the given room is occupied, use method with 2 arguments");
    }
  }

  public void putInTheRoom(int numberOfRoom, String fullNameOfResident) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfBookedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfBookedRoom == -1) {
      throw new UnsupportedOperationException(
          "the given room is not occupied, use method with 6 arguments");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (bookedRooms.get(indexOfBookedRoom).getHotelRoom().getRoomCapacity()
        > bookedRooms.get(indexOfBookedRoom).getResidents().size()) {

      bookedRooms.get(indexOfBookedRoom)
          .getResidents().add(residents.get(indexOfResident));
    } else {
      throw new UnsupportedOperationException("Maximum Size "
          + allRooms.get(indexOfRoom).getRoomCapacity() + " reached");
    }
  }

  public void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    int indexOfBookedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    if (indexOfBookedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (bookedRooms.get(indexOfBookedRoom).getResidents().size() == 1) {
      evictFromTheRoom(numberOfRoom);
      return;
    }

    bookedRooms.get(indexOfBookedRoom).getResidents()
        .remove(bookedRooms.get(indexOfBookedRoom)
            .getResidents().get(indexOfResidentInRoom));
  }

  public void evictFromTheRoom(int numberOfRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfBookedRoom = findIndexOfCheckInRegistration(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfBookedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    bookedRooms.remove(bookedRooms.get(indexOfBookedRoom));
    allRooms.get(indexOfRoom).setRoomIsOccupied(false);
  }

  public void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    if (findIndexOfRoom(numberOfRoom) == -1) {
      allRooms.add(new HotelRoom(numberOfRoom, numberOfStars, roomCapacity, price));
    } else {
      throw new IllegalArgumentException("this room already exists");
    }
  }

  public void addNewResident(String fullName, int passportNumber) {
    residents.add(new HotelResident(fullName, passportNumber));
  }

  public void addNewService(String name, int price) {
    this.services.add(new Service(name, price));
  }

  public void addServiceToBookedRoom(int numberOfRoom, String nameOfService) {
    int indexOfBookedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfService = findIndexOfService(nameOfService);

    if (indexOfBookedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (indexOfService == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    bookedRooms.get(indexOfBookedRoom)
        .getServices().add(services.get(indexOfService));
  }

  public void showCurrentServices() {
    for (Service service : services) {
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

    services.get(indexOfService).setPrice(newPrice);
  }

  private int findIndexOfRoom(int numberOfRoom) {
    int indexOfRoom = -1;

    for (int i = 0; i < allRooms.size(); i++) {
      if (allRooms.get(i).getNumberOfRoom() == numberOfRoom) {
        indexOfRoom = i;
        break;
      }
    }
    return indexOfRoom;
  }

  private int findIndexOfService(String name) {
    int indexOfService = -1;

    for (int i = 0; i < services.size(); i++) {
      if (services.get(i).getName().equals(name)) {
        indexOfService = i;
        break;
      }
    }
    return indexOfService;
  }

  private int findIndexOfCheckInRegistration(int numberOfRoom) {
    int indexOfCheckInRegistration = -1;

    for (int i = 0; i < bookedRooms.size(); i++) {
      if (bookedRooms.get(i).getHotelRoom().getNumberOfRoom() == numberOfRoom) {
        indexOfCheckInRegistration = i;
        break;
      }
    }
    return indexOfCheckInRegistration;
  }

  private int findIndexOfResident(String name) {
    int indexOfResident = -1;

    for (int i = 0; i < residents.size(); i++) {
      if (residents.get(i).fullName().equals(name)) {
        indexOfResident = i;
        break;
      }
    }
    return indexOfResident;
  }

  public void showAllRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>(allRooms);

    switch (criterion) {

      case PRICE -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getPrice));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }

      case ROOM_CAPACITY -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getRoomCapacity));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }

      case NUMBER_OF_STARS -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getNumberOfStars));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }
    }
  }

  public void showFreeRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>();

    for (HotelRoom hotelRoom : allRooms) {
      if (!hotelRoom.isRoomIsOccupied()) {
        listForSorting.add(hotelRoom);
      }
    }

    switch (criterion) {

      case PRICE -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getPrice));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }

      case ROOM_CAPACITY -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getRoomCapacity));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }

      case NUMBER_OF_STARS -> {
        listForSorting.sort(Comparator.comparing(HotelRoom::getNumberOfStars));
        for (HotelRoom hotelRoom : listForSorting) {
          System.out.println(hotelRoom.toString());
        }
      }
    }
  }

  public void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    List<CheckInRegistration> listForSorting = new ArrayList<>();

    for (CheckInRegistration checkInRegistration : bookedRooms) {
      if (checkInRegistration.getHotelRoom().isRoomIsOccupied()) {
        listForSorting.add(checkInRegistration);
      }
    }

    switch (criterion) {

      case ALPHABETICALLY -> {
        for (CheckInRegistration checkInRegistration : listForSorting) {
          checkInRegistration.getResidents().sort(Comparator.comparing(HotelResident::fullName));
        }

        listForSorting.sort(new Comparator<CheckInRegistration>() {
          @Override
          public int compare(CheckInRegistration o1, CheckInRegistration o2) {
            return o1.getResidents().get(0).fullName()
                .compareTo(o2.getResidents().get(0).fullName());
          }
        });

        for (CheckInRegistration checkInRegistration : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(checkInRegistration.getHotelRoom().getNumberOfRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : checkInRegistration.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder.toString());
        }
      }

      case ROOM_RELEASE_DATE -> {
        listForSorting.sort(Comparator.comparing(CheckInRegistration::getDepartureDate));

        for (CheckInRegistration checkInRegistration : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(checkInRegistration.getHotelRoom().getNumberOfRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : checkInRegistration.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder.toString());
        }
      }
    }
  }

  public void showNumberOfFreeRooms() {
    int size = 0;

    for (HotelRoom hotelRoom : allRooms) {
      if (!hotelRoom.isRoomIsOccupied()) {
        size += 1;
      }
    }
    System.out.println("Total number of free rooms: " + size);
  }

  public void showNumberOfCurrentResidents() {
    int size = 0;

    for (CheckInRegistration checkInRegistration : bookedRooms) {
      if (checkInRegistration.getHotelRoom().isRoomIsOccupied()) {
        size += checkInRegistration.getResidents().size();
      }
    }
    System.out.println("Total number of current residents: " + size);
  }
}
