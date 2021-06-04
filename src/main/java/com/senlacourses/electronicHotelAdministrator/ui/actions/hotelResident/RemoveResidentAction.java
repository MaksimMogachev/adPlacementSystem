package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelResidentController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class RemoveResidentAction implements IAction {

  private final IHotelResidentController controller;

  public RemoveResidentAction(IHotelResidentController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int passportNumber;

    System.out.print("Enter passport number: ");
    passportNumber = scanner.nextInt();
    controller.removeResident(passportNumber);
  }
}
