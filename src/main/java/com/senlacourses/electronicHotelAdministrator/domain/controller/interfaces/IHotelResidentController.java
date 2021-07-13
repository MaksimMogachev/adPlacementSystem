package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import org.springframework.http.ResponseEntity;

public interface IHotelResidentController {

  ResponseEntity<?> showAllResidents();

  ResponseEntity<?> addNewResident(HotelResident hotelResident);

  ResponseEntity<?> removeResident(int passportNumber);
}
