package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.service.HotelRoomService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class ShowRoomDetailsAction implements IAction {

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    new HotelRoomService().showRoomDetails(numberOfRoom);
  }
}
