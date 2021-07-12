package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import org.springframework.http.ResponseEntity;

public interface IHotelResidentController {

  ResponseEntity<?> showAllResidents();

  ResponseEntity<?> addNewResident(String fullName, int passportNumber);

  ResponseEntity<?> removeResident(int passportNumber);
}
