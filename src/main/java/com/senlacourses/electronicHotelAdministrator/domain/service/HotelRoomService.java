package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.RegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.RoomSortingCriteria;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelRoomService {

  private HotelRoomDao hotelRoomDao = HotelRoomDao.getInstance();
  private RegistrationCardDao registrationCardDao = RegistrationCardDao.getInstance();

  public List<HotelRoom> getAllRooms() {
    return hotelRoomDao.getAll();
  }

  public void showAllRooms() {
    for (HotelRoom hotelRoom : hotelRoomDao.getAll()) {
      System.out.println(hotelRoom.toString());
    }
  }

  public void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    if (findIndexOfRoom(numberOfRoom) == -1) {
      HotelRoom hotelRoom = new HotelRoom(numberOfRoom);
      hotelRoom.setNumberOfStars(numberOfStars);
      hotelRoom.setRoomCapacity(roomCapacity);
      hotelRoom.setPrice(price);
      hotelRoomDao.create(hotelRoom);
    } else {
      throw new IllegalArgumentException("this room already exists");
    }
  }

  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (hotelRoomDao.read(indexOfRoom).isRoomIsOccupied()) {
      throw new UnsupportedOperationException(
          "the room must be vacated before changing the condition");
    }

    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);
    hotelRoom.setRoomCondition(roomCondition);

    hotelRoomDao.update(hotelRoom, indexOfRoom);
  }

  public void changeRoomPrice(int numberOfRoom, int newPrice) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (newPrice < 0) {
      throw new IllegalArgumentException("incorrect price");
    }

    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);
    hotelRoom.setPrice(newPrice);

    hotelRoomDao.update(hotelRoom, indexOfRoom);
  }

  public void showNumberOfFreeRooms() {
    System.out.println("Total number of free rooms: "
        + (hotelRoomDao.getAll().size() - registrationCardDao.getAll().size()));
  }

  public void showAllRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>(hotelRoomDao.getAll());

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

    for (HotelRoom hotelRoom : hotelRoomDao.getAll()) {
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

  public void showFreeRoomsByDate(int year, int month, int dayOfMonth) {
    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {

      if (LocalDate.of(year, month, dayOfMonth)
          .isAfter(registrationCard.getDepartureDate())
          || (LocalDate.of(year, month, dayOfMonth)
          .equals(registrationCard.getDepartureDate()))) {

        System.out.println(registrationCard.getHotelRoom().toString());
      }
    }

    for (HotelRoom hotelRoom : hotelRoomDao.getAll()) {
      if (!hotelRoom.isRoomIsOccupied()) {
        System.out.println(hotelRoom.toString());
      }
    }
  }

  public void showLastResidentsOfRoom(int numberOfRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("Incorrect argument");
    }
    System.out.println("Last residents of room " + numberOfRoom + ": ");
    for (String string : hotelRoomDao.read(indexOfRoom).getLastResidents()) {
      System.out.println(string);
    }
  }

  public void showRoomDetails(int numberOfRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("Incorrect argument");
    }
    System.out.println(hotelRoomDao.read(indexOfRoom).toString());
  }

  private int findIndexOfRoom(int numberOfRoom) {
    int indexOfRoom = -1;

    for (int i = 0; i < hotelRoomDao.getAll().size(); i++) {
      if (hotelRoomDao.read(i).getNumberOfRoom() == numberOfRoom) {
        indexOfRoom = i;
        break;
      }
    }
    return indexOfRoom;
  }
}
