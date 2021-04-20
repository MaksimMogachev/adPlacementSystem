package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowNumberOfFreeRoomsAction implements IAction {

  private final IHotelRoomController controller;

  public ShowNumberOfFreeRoomsAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    controller.showNumberOfFreeRooms();
  }
}
