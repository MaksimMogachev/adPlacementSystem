package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.IGenericDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IRegistrationCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
public class RegistrationCardService implements IRegistrationCardService {

  private final Logger logger = LoggerFactory.getLogger(RegistrationCardService.class);
  private final IGenericDao<RegistrationCard> registrationCardDao;
  private final IGenericDao<HotelResident> hotelResidentDao;
  private final IGenericDao<HotelRoom> hotelRoomDao;
  private final IGenericDao<Service> serviceDao;

  public RegistrationCardService(IGenericDao<RegistrationCard> registrationCardDao,
                                 IGenericDao<HotelResident> hotelResidentDao,
                                 IGenericDao<HotelRoom> hotelRoomDao,
                                 IGenericDao<Service> serviceDao) {
    this.registrationCardDao = registrationCardDao;
    this.hotelResidentDao = hotelResidentDao;
    this.hotelRoomDao = hotelRoomDao;
    this.serviceDao = serviceDao;
  }

  @Override
  public List<RegistrationCard> showOccupiedRooms() {
    return registrationCardDao.getAll();
  }

  @Transactional
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

  @Transactional
  @Override
  public RegistrationCard putInTheRoom(int numberOfRoom, int passportNumber) {
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
    return registrationCard;
  }

  @Transactional
  @Override
  public RegistrationCard evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
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
      logger.error("IllegalArgumentException(\"the given room has only 1 resident, use method with 3 arguments\")");
      throw new IllegalArgumentException("the given room has only 1 resident, use method with 3 arguments");
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

    return registrationCard;
  }

  @Transactional
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

  @Transactional
  @Override
  public RegistrationCard addServiceToOccupiedRoom(int numberOfRoom, String nameOfService) {;
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
    return registrationCard;
  }

  @Override
  public List<RegistrationCard> showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    List<RegistrationCard> listForSorting = new ArrayList<>(registrationCardDao.getAll());

    switch (criterion) {

      case ALPHABETICALLY:
        listForSorting.forEach(registrationCard ->
            registrationCard.getResidents().sort(Comparator.comparing(HotelResident::getFullName)));

        listForSorting.sort(Comparator.comparing(o -> o.getResidents().get(0).getFullName()));

        return listForSorting;

      case ROOM_RELEASE_DATE:
        listForSorting.sort(Comparator.comparing(RegistrationCard::getDepartureDate));

        return listForSorting;
      }

      return null;
    }

  @Override
  public String showNumberOfCurrentResidents() {
    int size = 0;

    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {
      if (hotelRoomDao.read(registrationCard.getHotelRoom()).isRoomIsOccupied()) {
        size += registrationCard.getResidents().size();
      }
    }
    return ("Total number of current residents: " + size);
  }

  @Override
  public String showAmountOfPayment(int numberOfRoom, int daysOfStay) {
    HotelRoom hotelRoom = hotelRoomDao.read(numberOfRoom);

    return ("Amount of payment for the room: "
        + (hotelRoom.getPrice() * daysOfStay));
  }

  @Override
  public Object[] showResidentServicesByCriterion(int passportNumber,
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
        return services.entrySet().toArray();

    case PRICE:
        return services.entrySet().stream()
                .sorted(Comparator.comparingInt(o -> o.getValue().getPrice())).toArray();

      }
    return null;
    }
}
