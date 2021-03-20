package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelResident;

import com.senlacourses.electronicHotelAdministrator.domain.service.HotelResidentService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class RemoveResidentAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    String fullName;


    System.out.print("Enter full name: ");
    fullName = scanner.nextLine();
    new HotelResidentService().removeResident(fullName);
  }
}
