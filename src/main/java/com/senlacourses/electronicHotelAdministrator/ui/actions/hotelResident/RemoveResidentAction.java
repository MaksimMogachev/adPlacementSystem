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
    String fullName;

    System.out.print("Enter full name: ");
    fullName = scanner.nextLine();
    controller.removeResident(fullName);
  }
}
