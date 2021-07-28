package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelResidentDto;
import org.springframework.http.ResponseEntity;

public interface IHotelResidentController {

  ResponseEntity<?> showAllResidents();

  ResponseEntity<?> addNewResident(HotelResidentDto hotelResidentDto);

  ResponseEntity<?> removeResident(int passportNumber);
}
