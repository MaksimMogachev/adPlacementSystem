package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.CheckInRegistrationDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelResidentDao;
import com.senlacourses.electronicHotelAdministrator.dao.HotelRoomDao;
import com.senlacourses.electronicHotelAdministrator.dao.ServiceDao;
import com.senlacourses.electronicHotelAdministrator.domain.model.CheckInRegistration;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.model.ServiceSortingCriteria;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CheckInRegistrationService {

  private CheckInRegistrationDao checkInRegistrationDao = CheckInRegistrationDao.getInstance();
  private HotelResidentDao hotelResidentDao = HotelResidentDao.getInstance();
  private HotelRoomDao hotelRoomDao = HotelRoomDao.getInstance();
  private ServiceDao serviceDao = ServiceDao.getInstance();


  public List<CheckInRegistration> getOccupiedRooms() {
    return checkInRegistrationDao.getAll();
  }

  public void showOccupiedRooms() {
    for (CheckInRegistration checkInRegistration : checkInRegistrationDao.getAll()) {
      System.out.println(checkInRegistration.toString());
    }
  }

  public void putInTheRoom(int numberOfRoom, String fullNameOfResident, int daysOfStay) {
    int indexOfRoom = findIndexOfRoom(numberOfRoom);
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfOccupiedRoom == -1) {
      checkInRegistrationDao.create(new CheckInRegistration(hotelRoomDao.read(indexOfRoom),
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
    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    int indexOfResident = findIndexOfResident(fullNameOfResident);

    if (indexOfOccupiedRoom == -1) {
      throw new UnsupportedOperationException(
          "the given room is not occupied, use method with 3 arguments");
    }

    if (indexOfResident == -1) {
      throw new IllegalArgumentException("the given resident is not registered");
    }

    if (indexOfRoom == -1) {
      throw new IllegalArgumentException("this room does not exist");
    }

    if (checkInRegistrationDao.read(indexOfOccupiedRoom).getHotelRoom().getRoomCapacity()
        > checkInRegistrationDao.read(indexOfOccupiedRoom).getResidents().size()) {

      CheckInRegistration checkInRegistration = checkInRegistrationDao.read(indexOfOccupiedRoom);
      checkInRegistration.getResidents().add(hotelResidentDao.read(indexOfResident));

      checkInRegistrationDao.update(checkInRegistration, indexOfOccupiedRoom);
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

    int indexOfOccupiedRoom = findIndexOfCheckInRegistration(numberOfRoom);
    if (indexOfOccupiedRoom == -1) {
      throw new IllegalArgumentException("this room is not occupied");
    }

    if (checkInRegistrationDao.read(indexOfOccupiedRoom).getResidents().size() == 1) {
      evictFromTheRoom(numberOfRoom);
      return;
    }
    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);

    if (hotelRoom.getLastResidents().size() == 3) {
      hotelRoom.getLastResidents().remove(0);
    }

    String stringBuilder = checkInRegistrationDao.read(indexOfOccupiedRoom)
        .getResidents().get(indexOfResidentInRoom).toString() + "; "
        + checkInRegistrationDao.read(indexOfOccupiedRoom).getCheckInDate().toString()
        + " - " + checkInRegistrationDao.read(indexOfOccupiedRoom).getDepartureDate().toString();

    hotelRoom.getLastResidents().add(stringBuilder);
    hotelRoomDao.update(hotelRoom, indexOfRoom);

    CheckInRegistration checkInRegistration = checkInRegistrationDao.read(indexOfOccupiedRoom);
    checkInRegistration.getResidents().remove(checkInRegistrationDao
        .read(indexOfOccupiedRoom).getResidents().get(indexOfResidentInRoom));

    checkInRegistrationDao.update(checkInRegistration, indexOfOccupiedRoom);
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

    HotelRoom hotelRoom = hotelRoomDao.read(indexOfRoom);

    for (int i = 0; i < checkInRegistrationDao.read(indexOfOccupiedRoom).getResidents().size();
        i++) {
      if (hotelRoom.getLastResidents().size() == 3) {
        hotelRoom.getLastResidents().remove(0);
      }

      String stringBuilder = checkInRegistrationDao.read(indexOfOccupiedRoom)
          .getResidents().get(i).toString() + "; "
          + checkInRegistrationDao.read(indexOfOccupiedRoom).getCheckInDate().toString()
          + " - " + checkInRegistrationDao.read(indexOfOccupiedRoom).getDepartureDate().toString();

      hotelRoom.getLastResidents().add(stringBuilder);
    }

    hotelRoom.setRoomIsOccupied(false);
    hotelRoomDao.update(hotelRoom, indexOfRoom);

    checkInRegistrationDao.delete(checkInRegistrationDao.read(indexOfOccupiedRoom));

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

    CheckInRegistration checkInRegistration = checkInRegistrationDao.read(indexOfOccupiedRoom);
    checkInRegistration.getServices().put(LocalDateTime.now(), serviceDao.read(indexOfService));
    checkInRegistrationDao.update(checkInRegistration, indexOfOccupiedRoom);
  }

  public void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion) {
    List<CheckInRegistration> listForSorting = new ArrayList<>();

    for (CheckInRegistration checkInRegistration : checkInRegistrationDao.getAll()) {
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

  public void showNumberOfCurrentResidents() {
    int size = 0;

    for (CheckInRegistration checkInRegistration : checkInRegistrationDao.getAll()) {
      if (checkInRegistration.getHotelRoom().isRoomIsOccupied()) {
        size += checkInRegistration.getResidents().size();
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

    for (int i = 0; i < checkInRegistrationDao.getAll().size(); i++) {
      for (int j = 0; j < checkInRegistrationDao.read(i).getResidents().size(); j++) {
        if (checkInRegistrationDao.read(i).getResidents().get(j).fullName().equals(fullName)) {
          services = checkInRegistrationDao.read(i).getServices();
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

  private int findIndexOfCheckInRegistration(int numberOfRoom) {
    int indexOfCheckInRegistration = -1;

    for (int i = 0; i < checkInRegistrationDao.getAll().size(); i++) {
      if (checkInRegistrationDao.read(i).getHotelRoom().getNumberOfRoom() == numberOfRoom) {
        indexOfCheckInRegistration = i;
        break;
      }
    }
    return indexOfCheckInRegistration;
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
