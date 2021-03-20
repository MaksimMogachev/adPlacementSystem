package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class AddServiceToOccupiedRoomAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    String nameOfService;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("\nEnter name of service: ");
    nameOfService = scanner.nextLine();
    new RegistrationCardService().addServiceToOccupiedRoom(numberOfRoom,nameOfService);
  }
}
