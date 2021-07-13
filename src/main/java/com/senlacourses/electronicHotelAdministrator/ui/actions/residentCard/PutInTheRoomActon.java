package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class PutInTheRoomActon implements IAction {

  private final IRegistrationCardController controller;

  public PutInTheRoomActon(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    int passportNumber;
    int daysOfStay;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("Enter passport number of resident: ");
    passportNumber = scanner.nextInt();
    System.out.print(
        "Enter days of stay or press '0', if this room is already occupied by someone: ");
    daysOfStay = scanner.nextInt();
    if (daysOfStay == 0) {
//      controller.putInTheRoom(numberOfRoom, passportNumber);
      return;
    }
//    controller.putInTheRoom(numberOfRoom, passportNumber, daysOfStay);
  }
}
