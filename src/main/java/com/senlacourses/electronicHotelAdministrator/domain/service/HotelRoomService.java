package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelRoomDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.RoomCondition;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.RoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelRoomService;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HotelRoomService implements IHotelRoomService {

  private final static Logger logger = LoggerFactory.getLogger(HotelRoomService.class);
  private final IGenericDao<HotelRoom> hotelRoomDao;
  private final IGenericDao<RegistrationCard> registrationCardDao;

  public HotelRoomService(IGenericDao<HotelRoom> hotelRoomDao,
                          IGenericDao<RegistrationCard> registrationCardDao) {
    this.hotelRoomDao = hotelRoomDao;
    this.registrationCardDao = registrationCardDao;
  }

  @Override
  public List<HotelRoom> getAllRooms() {
    return hotelRoomDao.getAll();
  }

  @Transactional
  @Override
  public void addNewRoom(HotelRoomDto hotelRoomDto) {
    if (hotelRoomDao.read(hotelRoomDto.getNumberOfRoom()) != null) {
      logger.error("IllegalArgumentException(\"this room already exists\")");
      throw new IllegalArgumentException("this room already exists");
    }

    HotelRoom hotelRoom = new HotelRoom();
    hotelRoom.setNumberOfRoom(hotelRoomDto.getNumberOfRoom());
    hotelRoom.setNumberOfStars(hotelRoomDto.getNumberOfStars());
    hotelRoom.setRoomCapacity(hotelRoomDto.getRoomCapacity());
    hotelRoom.setPrice(hotelRoomDto.getPrice());

    hotelRoomDao.create(hotelRoom);
  }

  @Transactional
  @Override
  public HotelRoom changeRoomCondition(int numberOfRoom, RoomCondition roomCondition) {
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
      return null;
    }

    hotelRoom.setRoomCondition(roomCondition);
    hotelRoomDao.update(hotelRoom);
    return hotelRoom;
  }

  @Transactional
  @Override
  public HotelRoom changeRoomPrice(int numberOfRoom, int newPrice) {
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
    return hotelRoom;
  }

  @Override
  public String showNumberOfFreeRooms() {
    return ("Total number of free rooms: "
            + (hotelRoomDao.getAll().size() - registrationCardDao.getAll().size()));
  }

  @Override
  public List<HotelRoom> showAllRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>(hotelRoomDao.getAll());

    switch (criterion) {

      case PRICE:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getPrice))
                .collect(Collectors.toList());

      case ROOM_CAPACITY:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getRoomCapacity))
                .collect(Collectors.toList());

      case NUMBER_OF_STARS:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getNumberOfStars))
                .collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public List<HotelRoom> showFreeRoomsByCriterion(RoomSortingCriteria criterion) {
    List<HotelRoom> listForSorting = new ArrayList<>();

    hotelRoomDao.getAll().stream().filter(hotelRoom -> !hotelRoom.isRoomIsOccupied())
            .forEach(listForSorting::add);

    switch (criterion) {

      case PRICE:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getPrice))
                .collect(Collectors.toList());

      case ROOM_CAPACITY:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getRoomCapacity))
                .collect(Collectors.toList());

      case NUMBER_OF_STARS:
        return listForSorting.stream()
                .sorted(Comparator.comparing(HotelRoom::getNumberOfStars))
                .collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public List<HotelRoom> showRoomsByDate(int year, int month, int dayOfMonth) {
    List<HotelRoom> hotelRooms = new ArrayList<>();

    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {

      if (LocalDate.of(year, month, dayOfMonth)
              .isAfter(registrationCard.getDepartureDate())
              || (LocalDate.of(year, month, dayOfMonth)
              .equals(registrationCard.getDepartureDate()))) {

        hotelRooms.add(hotelRoomDao.read(registrationCard.getHotelRoom()));
      }
    }
    return hotelRooms;
  }

  @Override
  public List<String> showLastResidentsOfRoom(int numberOfRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }

    return hotelRoom.getLastResidents();
  }

  @Override
  public String showRoomDetails(int numberOfRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"Incorrect argument\")");
      throw new IllegalArgumentException("Incorrect argument");
    }
    return hotelRoom.toString();
  }
}
