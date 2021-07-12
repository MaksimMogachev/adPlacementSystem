package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import org.springframework.http.ResponseEntity;

public interface IRegistrationCardController {

  ResponseEntity<?> showOccupiedRooms();

  ResponseEntity<?> putInTheRoom(int numberOfRoom, int passportNumber, int daysOfStay);

  ResponseEntity<?> putInTheRoom(int numberOfRoom, int passportNumber);

  ResponseEntity<?> evictFromTheRoom(int numberOfRoom, int indexOfResidentInRoom);

  ResponseEntity<?> evictFromTheRoom(int numberOfRoom);

  ResponseEntity<?> addServiceToOccupiedRoom(int numberOfRoom, String nameOfService);

  ResponseEntity<?> showOccupiedRoomsByCriterion(String criterion);

  ResponseEntity<?> showNumberOfCurrentResidents();

  ResponseEntity<?> showAmountOfPayment(int numberOfRoom, int daysOfStay);

  ResponseEntity<?> showResidentServicesByCriterion(int passportNumber, String sortingCriteria);
}
