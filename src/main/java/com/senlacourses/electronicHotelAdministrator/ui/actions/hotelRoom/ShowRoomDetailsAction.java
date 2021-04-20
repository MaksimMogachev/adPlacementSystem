package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowRoomDetailsAction implements IAction {

  private final IHotelRoomController controller;

  public ShowRoomDetailsAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    controller.showRoomDetails(numberOfRoom);
  }
}
