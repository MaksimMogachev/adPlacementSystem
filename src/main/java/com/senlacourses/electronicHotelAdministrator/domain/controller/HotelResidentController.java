package com.senlacourses.electronicHotelAdministrator.domain.controller;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelResidentController;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IHotelResidentService;

public class HotelResidentController implements IHotelResidentController {

  private final IHotelResidentService hotelResidentService;

  public HotelResidentController(IHotelResidentService hotelResidentService) {
    this.hotelResidentService = hotelResidentService;
  }

  @Override
  public void showAllResidents() {
    hotelResidentService.showAllResidents();
  }

  @Override
  public void addNewResident(String fullName, int passportNumber) {
    hotelResidentService.addNewResident(fullName, passportNumber);
  }

  @Override
  public void removeResident(String fullName) {
    hotelResidentService.removeResident(fullName);
  }
}
