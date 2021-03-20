package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident;

import com.senlacourses.electronicHotelAdministrator.domain.service.HotelResidentService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowAllResidentsAction implements IAction {

  @Override
  public void execute() {
    new HotelResidentService().showAllResidents();
  }
}
