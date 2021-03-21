package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.RegistrationCardDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.ServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RegistrationCardService {

  private RegistrationCardDao registrationCardDao = RegistrationCardDao.getInstance();
  private HotelResidentDao hotelResidentDao = HotelResidentDao.getInstance();
  private HotelRoomDao hotelRoomDao = HotelRoomDao.getInstance();
  private ServiceDao serviceDao = ServiceDao.getInstance();

  public List<RegistrationCard> getOccupiedRooms() {
    return registrationCardDao.getAll();
  }

  public void showOccupiedRooms() {
    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {
      System.out.println(registrationCard.toString());
    }
  }

  public void putInTheRoom(int numberOfRoom, String fullNameOfResident, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfRegistrationCard = findIndexOfRegistrationCard(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfRegistrationCard == -1) {
      registrationCardDao.create(new RegistrationCard(hotelRoomDao.read(indexOfRoom),
          hotelResidentDao.read(indexOfResident), daysOfStay));

      HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);
      hotelRoom.setRoomIsOccupied(true);
      hotelRoomDao.update(hotelRoom, indexOfRoom);

    } else {
      throw new UnsupportedOperationException(
          "the given room is occupied, use method with 2 arguments");
    }
  }

  public void putInTheRoom(int numberOfRoom, String fullNameOfResident) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfRegistrationCard = findIndexOfRegistrationCard(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfRegistrationCard == -1) {
      throw new UnsupportedOperationException(
          "the given room is not occupied, use method with 3 arguments");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (registrationCardDao.read(indexOfRegistrationCard).getHotelRoom().getRoomCapacity()
        > registrationCardDao.read(indexOfRegistrationCard).getResidents().size()) {

      RegistrationCard registrationCard = registrationCardDao.read(indexOfRegistrationCard);
      registrationCard.getResidents().add(hotelResidentDao.read(indexOfResident));

      registrationCardDao.update(registrationCard, indexOfRegistrationCard);
    } else {
      throw new UnsupportedOperationException("Maximum Size "
          + hotelRoomDao.read(indexOfRoom).getRoomCapacity() + " reached");
    }
  }

  public void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    int indexOfRegistrationCard = findIndexOfRegistrationCard(numberOfRoom);
    if (indexOfRegistrationCard == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (registrationCardDao.read(indexOfRegistrationCard).getResidents().size() == 1) {
      evictFromTheRoom(numberOfRoom);
      return;
    }
    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);

    if (hotelRoom.getLastResidents().size() == 3) {
      hotelRoom.getLastResidents().remove(0);
    }

    String stringBuilder = registrationCardDao.read(indexOfRegistrationCard)
        .getResidents().get(indexOfResidentInRoom).toString() + "; "
        + registrationCardDao.read(indexOfRegistrationCard).getCheckInDate().toString()
        + " - " + registrationCardDao.read(indexOfRegistrationCard).getDepartureDate().toString();

    hotelRoom.getLastResidents().add(stringBuilder);
    hotelRoomDao.update(hotelRoom, indexOfRoom);

    RegistrationCard registrationCard = registrationCardDao.read(indexOfRegistrationCard);
    registrationCard.getResidents().remove(registrationCardDao
        .read(indexOfRegistrationCard).getResidents().get(indexOfResidentInRoom));

    registrationCardDao.update(registrationCard, indexOfRegistrationCard);
  }

  public void evictFromTheRoom(int numberOfRoom) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfRegistrationCard = findIndexOfRegistrationCard(numberOfRoom);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfRegistrationCard == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);

    for (int i = 0; i < registrationCardDao.read(indexOfRegistrationCard).getResidents().size();
        i++) {
      if (hotelRoom.getLastResidents().size() == 3) {
        hotelRoom.getLastResidents().remove(0);
      }

      String stringBuilder = registrationCardDao.read(indexOfRegistrationCard)
          .getResidents().get(i).toString() + "; "
          + registrationCardDao.read(indexOfRegistrationCard).getCheckInDate().toString()
          + " - " + registrationCardDao.read(indexOfRegistrationCard).getDepartureDate().toString();

      hotelRoom.getLastResidents().add(stringBuilder);
    }

    hotelRoom.setRoomIsOccupied(false);
    hotelRoomDao.update(hotelRoom, indexOfRoom);

    registrationCardDao.delete(registrationCardDao.read(indexOfRegistrationCard));

  }

  public void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService) {
    int indexOfRegistrationCard = findIndexOfRegistrationCard(numberOfRoom);
    int indexOfService = findIndexOfService(nameOfService);

    if (indexOfRegistrationCard == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (indexOfService == -1) {
      throw new IllegalArgumentException("this service does not exist");
    }

    RegistrationCard registrationCard = registrationCardDao.read(indexOfRegistrationCard);
    registrationCard.getServices().put(LocalDateTime.now(), serviceDao.read(indexOfService));
    registrationCardDao.update(registrationCard, indexOfRegistrationCard);
  }

  public void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    List<RegistrationCard> listForSorting = new ArrayList<>(registrationCardDao.getAll());

    switch (criterion) {

      case ALPHABETICALLY -> {
        listForSorting.forEach(registrationCard ->
            registrationCard.getResidents().sort(Comparator.comparing(HotelResident::fullName)));

        listForSorting.sort(Comparator.comparing(o -> o.getResidents().get(0).fullName()));

        for (RegistrationCard registrationCard : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(registrationCard.getHotelRoom().getNumberOfRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : registrationCard.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder.toString());
        }
      }

      case ROOM_RELEASE_DATE -> {
        listForSorting.sort(Comparator.comparing(RegistrationCard::getDepartureDate));

        for (RegistrationCard registrationCard : listForSorting) {
          StringBuilder stringBuilder = new StringBuilder();

          stringBuilder.append("Hotel room: ")
              .append(registrationCard.getHotelRoom().getNumberOfRoom())
              .append("; Room residents: ");
          for (HotelResident hotelResident : registrationCard.getResidents()) {
            stringBuilder.append(hotelResident.toString());
          }

          System.out.println(stringBuilder.toString());
        }
      }
    }
  }

  public void showNumberOfCurrentResidents() {
    int size = 0;

    for (RegistrationCard registrationCard : registrationCardDao.getAll()) {
      if (registrationCard.getHotelRoom().isRoomIsOccupied()) {
        size += registrationCard.getResidents().size();
      }
    }
    System.out.println("Total number of current residents: " + size);
  }

  public void showAmountOfPayment(int numberOfRoom, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);

    System.out.println("Amount of payment for the room: "
        + (hotelRoomDao.read(indexOfRoom).getPrice() * daysOfStay));
  }

  public void showResidentServicesByCriterion(String fullName,
      ServiceSortingCriteria sortingCriteria) {
    Map<LocalDateTime, Service> services = null;

    for (int i = 0; i < registrationCardDao.getAll().size(); i++) {
      for (int j = 0; j < registrationCardDao.read(i).getResidents().size(); j++) {
        if (registrationCardDao.read(i).getResidents().get(j).fullName().equals(fullName)) {
          services = registrationCardDao.read(i).getServices();
          break;
        }
      }
    }
    if (services == null) {
      throw new IllegalArgumentException("incorrect argument");
    }

    switch (sortingCriteria) {

      case DATE -> {
        for (Map.Entry<LocalDateTime, Service> service : services.entrySet()) {
          System.out.println(service.getValue().toString() + ", " + service.getKey()
              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd")));
        }
      }

      case PRICE -> {
        List<Map.Entry<LocalDateTime, Service>> entryList = new ArrayList<>(services.entrySet());

        entryList.sort(Comparator.comparingInt(o -> o.getValue().getPrice()));

        for (Map.Entry<LocalDateTime, Service> map : entryList) {
          System.out.println(map.getValue().toString() + ", "
              + map.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd")));
        }
      }
    }
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

  private int findIndexOfRegistrationCard(int numberOfRoom) {
    int indexOfRegistrationCard = -1;

    for (int i = 0; i < registrationCardDao.getAll().size(); i++) {
      if (registrationCardDao.read(i).getHotelRoom().getNumberOfRoom() == numberOfRoom) {
        indexOfRegistrationCard = i;
        break;
      }
    }
    return indexOfRegistrationCard;
  }

  private int findIndexOfResident(String name) {
    int indexOfResident = -1;

    for (int i = 0; i < hotelResidentDao.getAll().size(); i++) {
      if (hotelResidentDao.read(i).fullName().equals(name)) {
        indexOfResident = i;
        break;
      }
    }
    return indexOfResident;
  }

  private int findIndexOfService(String name) {
    int indexOfService = -1;

    for (int i = 0; i < serviceDao.getAll().size(); i++) {
      if (serviceDao.read(i).getName().equals(name)) {
        indexOfService = i;
        break;
      }
    }
    return indexOfService;
  }
}
