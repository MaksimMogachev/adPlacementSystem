package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class AddServiceToOccupiedRoomAction implements IAction {

  private final IRegistrationCardController controller;

  public AddServiceToOccupiedRoomAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    String nameOfService;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("\nEnter name of service: ");
    nameOfService = scanner.next();
    controller.addServiceToOccupiedRoom(numberOfRoom, nameOfService);
  }
}
