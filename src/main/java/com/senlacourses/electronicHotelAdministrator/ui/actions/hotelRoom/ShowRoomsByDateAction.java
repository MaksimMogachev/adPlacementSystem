package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowRoomsByDateAction implements IAction {

  private IHotelRoomController controller;

  public ShowRoomsByDateAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int year;
    int month;
    int dayOfMonth;

    System.out.print("Enter year: ");
    year = scanner.nextInt();
    System.out.print("\nEnter month: ");
    month = scanner.nextInt();
    System.out.print("\nEnter dayOfMonth: ");
    dayOfMonth = scanner.nextInt();
    controller.showRoomsByDate(year, month, dayOfMonth);
  }
}
