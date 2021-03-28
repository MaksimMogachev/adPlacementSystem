package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;

public class ShowNumberOfFreeRoomsAction implements IAction {

  private IHotelRoomController controller;

  public ShowNumberOfFreeRoomsAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    controller.showNumberOfFreeRooms();
  }
}
