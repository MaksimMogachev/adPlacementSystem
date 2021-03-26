package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowLastResidentsOfRoomAction implements IAction {

  private IHotelRoomController controller;

  public ShowLastResidentsOfRoomAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    controller.showLastResidentsOfRoom(numberOfRoom);
  }
}
