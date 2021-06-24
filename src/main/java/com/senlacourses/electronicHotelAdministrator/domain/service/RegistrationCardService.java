package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.annotations.ConfigSingleton;
import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationCardService implements IRegistrationCardService {

  private final Logger logger = LoggerFactory.getLogger(RegistrationCardService.class);
  @ConfigSingleton(className = "RegistrationCardDao")
  private IGenericDao<RegistrationCard> registrationCardDao;
  @ConfigSingleton(className = "HotelResidentDao")
  private IGenericDao<HotelResident> hotelResidentDao;
  @ConfigSingleton(className = "HotelRoomDao")
  private IGenericDao<HotelRoom> hotelRoomDao;
  @ConfigSingleton(className = "ServiceDao")
  private IGenericDao<Service> serviceDao;

  @Override
  public void showOccupiedRooms() {
    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {
      System.out.println(registrationCard.toString());
    }
  }

  @Override
  public void putInTheRoom(int numberOfRoom, int passportNumber, int daysOfStay) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);
    RegistrationCard registrationCard = registrationCardDao.read(numberOfRoom);
    HotelResident hotelResident = hotelResidentDao.read(passportNumber);

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    }

    if (hotelResident == null) {
      logger.error("IllegalArgumentException(\"the given resident is not registered\")");
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (registrationCard == null) {
      registrationCard = new RegistrationCard();
      registrationCard.setHotelRoom(numberOfRoom);
      registrationCard.getResidents().add(hotelResident);
      registrationCard.setCheckInDate(LocalDate.now());
      registrationCard.setDepartureDate(LocalDate.now().plusDays(daysOfStay));
      registrationCardDao.create(registrationCard);

      hotelRoom.setRoomIsOccupied(true);
      hotelRoomDao.update(hotelRoom);

    } else {
      logger.error("IUnsupportedOperationException(\n"
          + "\"the given room is occupied, use method with 2 arguments\")");
      throw new UnsupportedOperationException(
          "the given room is occupied, use method with 2 arguments");
    }
  }

  @Override
  public void putInTheRoom(int numberOfRoom, int passportNumber) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);
    RegistrationCard registrationCard = registrationCardDao.read(numberOfRoom);
    HotelResident hotelResident = hotelResidentDao.read(passportNumber);

    if (registrationCard == null) {
      logger.error("UnsupportedOperationException(\n"
          + "\"the given room is not occupied, use method with 3 arguments\")");
      throw new UnsupportedOperationException(
          "the given room is not occupied, use method with 3 arguments");
    }

    if (hotelResident == null) {
      logger.error("IllegalArgumentException(\"the given resident is not registered\")");
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    }

    if (hotelRoom.getRoomCapacity()
        > registrationCard.getResidents().size()) {

      registrationCard.getResidents().add(hotelResident);
      registrationCardDao.update(registrationCard);

    } else {
      logger.error("new UnsupportedOperationException(\"Maximum Size "
          + hotelRoom.getRoomCapacity() + " reached\")");
      throw new UnsupportedOperationException("Maximum Size "
          + hotelRoom.getRoomCapacity() + " reached");
    }
  }

  @Override
  public void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);
    RegistrationCard registrationCard = registrationCardDao.read(numberOfRoom);
    Properties properties = new Properties();

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    }

    if (registrationCard == null) {
      logger.error("IllegalArgumentException(\"this room does not occupied\")");
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (registrationCard.getResidents().size() == 1) {
      evictFromTheRoom(numberOfRoom);
      return;
    }

    try {
      properties.load(new FileInputStream("src/main/resources/config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (hotelRoom.getLastResidents().size()
        == Integer.parseInt(properties.getProperty("numberOfResidentRecords"))) {
      hotelRoom.getLastResidents().remove(0);
    }

    String stringBuilder = registrationCard
        .getResidents().get(indexOfResidentInRoom).toString() + "; "
        + registrationCard.getCheckInDate().toString()
        + " - " + registrationCard.getDepartureDate().toString();

    hotelRoom.getLastResidents().add(stringBuilder);
    hotelRoomDao.update(hotelRoom);

    registrationCard.getResidents().remove(registrationCard.getResidents().get(indexOfResidentInRoom));

    registrationCardDao.update(registrationCard);
  }

  @Override
  public void evictFromTheRoom(int numberOfRoom) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);
    RegistrationCard registrationCard = registrationCardDao.read(numberOfRoom);
    Properties properties = new Properties();

    if (hotelRoom == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this room does not exist");
    }

    if (registrationCard == null) {
      logger.error("IllegalArgumentException(\"this room does not occupied\")");
      throw new IllegalArgumentException("this room is not occupied");
    }

    try {
      properties.load(new FileInputStream("src/main/resources/config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < registrationCard.getResidents().size();
        i++) {
      if (hotelRoom.getLastResidents().size()
          == Integer.parseInt(properties.getProperty("numberOfResidentRecords"))) {
        hotelRoom.getLastResidents().remove(0);
      }

      String stringBuilder = registrationCard
          .getResidents().get(i).toString() + "; "
          + registrationCard.getCheckInDate().toString()
          + " - " + registrationCard.getDepartureDate().toString();

      hotelRoom.getLastResidents().add(stringBuilder);
    }

    hotelRoom.setRoomIsOccupied(false);
    hotelRoomDao.update(hotelRoom);

    registrationCardDao.delete(registrationCard);
  }

  @Override
  public void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService) {;
    RegistrationCard registrationCard = registrationCardDao.read(numberOfRoom);
    Service service = serviceDao.read(nameOfService);

    if (registrationCard == null) {
      logger.error("IllegalArgumentException(\"this room does not occupied\")");
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (service == null) {
      logger.error("IllegalArgumentException(\"this room does not exist\")");
      throw new IllegalArgumentException("this service does not exist");
    }

    registrationCard.getServices().put(LocalDateTime.now(), service);
    registrationCardDao.update(registrationCard);
  }

  @Override
  public void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    List<RegistrationCard> listForSorting = new ArrayList<>(registrationCardDao.getAll());

    switch (criterion) {

      case ALPHABETICALLY:
        listForSorting.forEach(registrationCard ->
            registrationCard.getResidents().sort(Comparator.comparing(HotelResident::getFullName)));

        listForSorting.sort(Comparator.comparing(o -> o.getResidents().get(0).getFullName()));

        for (RegistrationCard registrationCard : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(registrationCard.getHotelRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : registrationCard.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder);
        }
      break;

      case ROOM_RELEASE_DATE:
        listForSorting.sort(Comparator.comparing(RegistrationCard::getDepartureDate));

        for (RegistrationCard registrationCard : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(registrationCard.getHotelRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : registrationCard.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder);
        }
        break;
      }
    }

  @Override
  public void showNumberOfCurrentResidents() {
    int size = 0;

    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {
      if (hotelRoomDao.read(registrationCard.getHotelRoom()).isRoomIsOccupied()) {
        size += registrationCard.getResidents().size();
      }
    }
    System.out.println("Total number of current residents: " + size);
  }

  @Override
  public void showAmountOfPayment(int numberOfRoom, int daysOfStay) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    System.out.println("Amount of payment for the room: "
        + (hotelRoom.getPrice() * daysOfStay));
  }

  @Override
  public void showResidentServicesByCriterion(int passportNumber,
      ServiceSortingCriteria sortingCriteria) {
    Map<LocalDateTime, Service> services = null;

    for (int i = 0; i < registrationCardDao.getAll().size(); i++) {
      for (int j = 0; j < registrationCardDao.read(i).getResidents().size(); j++) {
        if (registrationCardDao.read(i).getResidents().get(j).getPassportNumber() == passportNumber) {
          services = registrationCardDao.read(i).getServices();
          break;
        }
      }
    }
    if (services == null) {
      logger.error("IllegalArgumentException(\"incorrect argument\")");
      throw new IllegalArgumentException("incorrect argument");
    }

    switch (sortingCriteria) {

      case DATE:
        for (Map.Entry<LocalDateTime, Service> service : services.entrySet()) {
          System.out.println(service.getValue().toString() + ", " + service.getKey()
              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd")));
        }
      break;

    case PRICE:
        List<Map.Entry<LocalDateTime, Service>> entryList = new ArrayList<>(services.entrySet());

        entryList.sort(Comparator.comparingInt(o -> o.getValue().getPrice()));

        for (Map.Entry<LocalDateTime, Service> map : entryList) {
          System.out.println(map.getValue().toString() + ", "
              + map.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd")));
        }
        break;
      }
    }
}
