package com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces;

public interface IHotelResidentController {

  void showAllResidents();

  void addNewResident(String fullName, int passportNumber);

  void removeResident(String fullName);
}
