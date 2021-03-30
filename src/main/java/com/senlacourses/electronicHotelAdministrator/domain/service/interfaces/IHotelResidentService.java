package com.senlacourses.electronicHotelAdministrator.domain.service.interfaces;

public interface IHotelResidentService {

  void showAllResidents();

  void addNewResident(String fullName, int passportNumber);

  void removeResident(String fullName);
}
