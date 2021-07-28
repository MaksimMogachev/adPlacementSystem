package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.RegistrationCardDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.OccupiedRoomSortingCriteria;
import com.senlacourses.electronicHotelAdministrator.domain.service.criteriaForSorting.ServiceSortingCriteria;

import java.util.List;

public interface IRegistrationCardService {

  List<RegistrationCard> getOccupiedRooms();

  void createNewCard(RegistrationCardDto registrationCardDto);

  RegistrationCard putInTheRoom(int numberOfRoom, int passportNumber);

  RegistrationCard evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom);

  void evictFromTheRoom(int numberOfRoom);

  RegistrationCard addServiceToOccupiedRoom(int numberOfRoom, String nameOfService);

  List<RegistrationCard> showOccupiedRoomsByCriterion(OccupiedRoomSortingCriteria criterion);

  String showNumberOfCurrentResidents();

  String showAmountOfPayment(int numberOfRoom, int daysOfStay);

  Object[] showResidentServicesByCriterion(int passportNumber, ServiceSortingCriteria sortingCriteria);
}
