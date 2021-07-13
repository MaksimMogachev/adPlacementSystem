package com.senlacourses.electronicHotelAdministrator.ui.actions.hotelRoom;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IHotelRoomController;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class AddNewRoomAction implements IAction {

  private final IHotelRoomController controller;

  public AddNewRoomAction(IHotelRoomController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    int numberOfStars;
    int price;
    int roomCapacity;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("Enter number of stars: ");
    numberOfStars = scanner.nextInt();
    System.out.print("Enter capacity of room: ");
    roomCapacity = scanner.nextInt();
    System.out.print("Enter price of room: ");
    price = scanner.nextInt();
//    controller.addNewRoom(numberOfRoom, numberOfStars, roomCapacity, price);
  }
}
