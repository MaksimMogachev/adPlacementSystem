package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowAmountOfPayment implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    int daysOfStay;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("\nEnter days of stay: ");
    daysOfStay = scanner.nextInt();
    new RegistrationCardService().showAmountOfPayment(numberOfRoom, daysOfStay);
  }
}
