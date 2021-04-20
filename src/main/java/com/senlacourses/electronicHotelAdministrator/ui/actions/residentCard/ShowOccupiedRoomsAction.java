package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowOccupiedRoomsAction implements IAction {

  private final IRegistrationCardController controller;

  public ShowOccupiedRoomsAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    controller.showOccupiedRooms();
  }
}
