package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class EvictFromTheRoomAction implements IAction {

  private IRegistrationCardController controller;

  public EvictFromTheRoomAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    String indexOfResidentInRoom;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print("\nEnter index of resident in room or press 'enter', if you want to evict everyone: ");
    indexOfResidentInRoom = scanner.nextLine();
    if (indexOfResidentInRoom == null) {
      new RegistrationCardService().evictFromTheRoom(numberOfRoom);
      return;
    }
    controller.evictFromTheRoom(numberOfRoom, Integer.parseInt(indexOfResidentInRoom));
  }
}
