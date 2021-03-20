package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class PutInTheRoomActon implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    String fullNameOfResident;
    String daysOfStay;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("Enter full name of resident: ");
    fullNameOfResident = scanner.nextLine();
    System.out.print("\nEnter days of stay or press 'enter', if this room is already occupied by someone: ");
    daysOfStay = scanner.nextLine();
    if (daysOfStay == null) {
      new RegistrationCardService().putInTheRoom(numberOfRoom, fullNameOfResident);
      return;
    }
    new RegistrationCardService()
        .putInTheRoom(numberOfRoom, fullNameOfResident, Integer.parseInt(daysOfStay));
  }
}
