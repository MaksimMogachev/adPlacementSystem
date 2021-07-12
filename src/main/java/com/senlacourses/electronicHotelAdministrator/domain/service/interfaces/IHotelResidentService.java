package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;

import java.util.List;

public interface IHotelResidentService {

  List<HotelResident> showAllResidents();

  void addNewResident(String fullName, int passportNumber);

  boolean removeResident(int passportNumber);
}
