package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;

public interface IRegistrationCardService {

  void showOccupiedRooms();

  void putInTheRoom(int numberOfRoom, int passportNumber, int daysOfStay);

  void putInTheRoom(int numberOfRoom, int passportNumber);

  void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom);

  void evictFromTheRoom(int numberOfRoom);

  void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService);

  void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion);

  void showNumberOfCurrentResidents();

  void showAmountOfPayment(int numberOfRoom, int daysOfStay);

  void showResidentServicesByCriterion(int passportNumber, ServiceSortingCriteria sortingCriteria);
}
