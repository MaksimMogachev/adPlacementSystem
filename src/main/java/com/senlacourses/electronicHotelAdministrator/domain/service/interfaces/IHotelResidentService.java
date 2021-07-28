package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.dto.request.HotelResidentDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

import java.util.List;

public interface IHotelResidentService {

  List<HotelResident> getAllResidents();

  void addNewResident(HotelResidentDto hotelResident);

  boolean removeResident(int passportNumber);
}
