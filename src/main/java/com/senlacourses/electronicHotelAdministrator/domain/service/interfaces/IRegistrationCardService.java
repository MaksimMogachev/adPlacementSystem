package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceSortingCriteria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IRegistrationCardService {

  List<RegistrationCard> showOccupiedRooms();

  void putInTheRoom(int numberOfRoom, int passportNumber, int daysOfStay);

  RegistrationCard putInTheRoom(int numberOfRoom, int passportNumber);

  RegistrationCard evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom);

  void evictFromTheRoom(int numberOfRoom);

  RegistrationCard addServiceToOccupiedRoom(int numberOfRoom, String nameOfService);

  List<RegistrationCard> showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion);

  String showNumberOfCurrentResidents();

  String showAmountOfPayment(int numberOfRoom, int daysOfStay);

  Object[] showResidentServicesByCriterion(int passportNumber, ServiceSortingCriteria sortingCriteria);
}
