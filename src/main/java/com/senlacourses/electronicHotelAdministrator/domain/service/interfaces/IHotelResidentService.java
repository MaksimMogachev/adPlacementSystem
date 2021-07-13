package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

import java.util.List;

public interface IHotelResidentService {

  List<HotelResident> showAllResidents();

  void addNewResident(HotelResident hotelResident);

  boolean removeResident(int passportNumber);
}
