package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.model.criteriaForSorting.ServiceSortingCriteria;

public interface IRegistrationCardController {

  void showOccupiedRooms();

  void putInTheRoom(int numberOfRoom, String fullNameOfResident, int daysOfStay);

  void putInTheRoom(int numberOfRoom, String fullNameOfResident);

  void evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom);

  void evictFromTheRoom(int numberOfRoom);

  void addServiceToOccupiedRoom(int numberOfRoom, String nameOfService);

  void showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion);

  void showNumberOfCurrentResidents();

  void showAmountOfPayment(int numberOfRoom, int daysOfStay);

  void showResidentServicesByCriterion(String fullName, ServiceSortingCriteria sortingCriteria);
}
