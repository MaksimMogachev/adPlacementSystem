package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowOccupiedRoomsAction implements IAction {

  @Override
  public void execute() {
    new RegistrationCardService().showOccupiedRooms();
  }
}
