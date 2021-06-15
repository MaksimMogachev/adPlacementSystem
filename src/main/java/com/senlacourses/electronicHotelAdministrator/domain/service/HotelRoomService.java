package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.RoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotelRoomService implements IHotelRoomService {

  private final static Logger logger = LoggerFactory.getLogger(HotelRoomService.class);
  private final IGenericDao<HotelRoom> hotelRoomDao;
  private final IGenericDao<RegistrationCard> registrationCardDao;

  public HotelRoomService(IGenericDao<HotelRoom> hotelRoomDao, IGenericDao<RegistrationCard> registrationCardDao) {
    this.hotelRoomDao = hotelRoomDao;
    this.registrationCardDao = registrationCardDao;
  }

  @Override
  public void showAllRooms() {
    hotelRoomDao.getAll().forEach(hotelRoom -> System.out.println(hotelRoom.toString()));
  }

  @Override
  public void addNewRoom(int numberOfRoom, int numberOfStars, int roomCapacity, int price) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

      if (hotelRoom == null) {
        hotelRoom = new HotelRoom();
        hotelRoom.setNumberOfRoom(numberOfRoom);
        hotelRoom.setNumberOfStars(numberOfStars);
        hotelRoom.setRoomCapacity(roomCapacity);
        hotelRoom.setPrice(price);
        hotelRoomDao.create(hotelRoom);
      } else {
        logger.error("IllegalArgumentException(\"this room already exists\")");
        throw new IllegalArgumentException("this room already exists");
      }
  }

  @Override
  public void changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
    Properties properties = new Properties();
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    } else {
      if (hotelRoom.isRoomIsOccupied()) {
        logger.error(
                "UnsupportedOperationException(\"the room must be vacated before changing the condition\")");
        throw new UnsupportedOperationException(
                "the room must be vacated before changing the condition");
      }
    }
    try {
      properties.load(new FileInputStream("src/main/resources/config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (properties.getProperty("changeNumberStatus").equals("off")) {
      System.out.println("no access to change the state of the room");
      return;
    }

    hotelRoom.setRoomCondition(roomCondition);
    hotelRoomDao.update(hotelRoom);
  }

  @Override
  public void changeRoomPrice(int numberOfRoom, int newPrice) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    }

    if (newPrice < 0) {
      logger.error("IllegalArgumentException(\"incorrect price\")");
      throw new IllegalArgumentException("incorrect price");
    }

    hotelRoom.setPrice(newPrice);
    hotelRoomDao.update(hotelRoom);
  }

  @Override
  public void showNumberOfFreeRooms() {
    System.out.println("Total number of free rooms: "
        + (hotelRoomDao.getAll().size() - registrationCardDao.getAll().size()));
  }

  @Override
  public void showAllRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>(hotelRoomDao.getAll());

    switch (criterion) {

      case PRICE: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getPrice))
          .forEach(System.out::println);
      break;

      case ROOM_CAPACITY: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getRoomCapacity))
          .forEach(System.out::println);
      break;

      case NUMBER_OF_STARS: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getNumberOfStars))
          .forEach(System.out::println);
      break;
    }
  }

  @Override
  public void showFreeRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>();

    hotelRoomDao.getAll().stream().filter(hotelRoom -> !hotelRoom.isRoomIsOccupied())
        .forEach(listForSorting::add);

    switch (criterion) {

      case PRICE: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getPrice))
          .forEach(System.out::println);
      break;

      case ROOM_CAPACITY: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getRoomCapacity))
          .forEach(System.out::println);
      break;

      case NUMBER_OF_STARS: listForSorting.stream()
          .sorted(Comparator.comparing(HotelRoom::getNumberOfStars))
          .forEach(System.out::println);
      break;
    }
  }

  @Override
  public void showRoomsByDate(int year, int month, int dayOfMonth) {
    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {

      if (LocalDate.of(year, month, dayOfMonth)
          .isAfter(registrationCard.getDepartureDate())
          || (LocalDate.of(year, month, dayOfMonth)
          .equals(registrationCard.getDepartureDate()))) {

        System.out.println(hotelRoomDao.read(registrationCard.getHotelRoom()).toString());
      }
    }
  }

  @Override
  public void showLastResidentsOfRoom(int numberOfRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }
    System.out.println("Last residents of room " + numberOfRoom + ": ");
    for (String string : hotelRoom.getLastResidents()) {
      System.out.println(string);
    }
  }

  @Override
  public void showRoomDetails(int numberOfRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }
    System.out.println(hotelRoom);
  }
}
