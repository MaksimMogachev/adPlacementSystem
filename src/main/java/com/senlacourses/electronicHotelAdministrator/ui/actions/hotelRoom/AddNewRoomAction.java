package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.service.HotelResidentService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class AddNewRoomAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    String fullName;
    int passportNumber;


    System.out.print("Enter full name: ");
    fullName = scanner.nextLine();
    System.out.print("\nEnter passport number: ");
    passportNumber = scanner.nextInt();
    new HotelResidentService().addNewResident(fullName, passportNumber);
  }
}
