package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelResidentController;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class AddNewResidentAction implements IAction {

  private final IHotelResidentController controller;

  public AddNewResidentAction(IHotelResidentController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    String fullName;
    int passportNumber;

    System.out.print("Enter full name: ");
    fullName = scanner.nextLine();
    System.out.print("\nEnter passport number: ");
    passportNumber = scanner.nextInt();
//    controller.addNewResident(fullName, passportNumber);
  }
}
