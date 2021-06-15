package com.senlacourses.electronicHotelAdministrator.ui.actions.residentCard;

import com.senlacourses.electronicHotelAdministrator.domain.controller.interfaces.IRegistrationCardController;
import com.senlacourses.electronicHotelAdministrator.domain.service.RegistrationCardService;
import com.senlacourses.electronicHotelAdministrator.ui.IAction;
import java.util.Scanner;

public class EvictFromTheRoomAction implements IAction {

  private final IRegistrationCardController controller;

  public EvictFromTheRoomAction(IRegistrationCardController controller) {
    this.controller = controller;
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    int numberOfRoom;
    int indexOfResidentInRoom;

    System.out.print("Enter number of room: ");
    numberOfRoom = scanner.nextInt();
    System.out.print(
        "\nEnter index of resident in room or enter '-1', if you want to evict everyone: ");
    indexOfResidentInRoom = scanner.nextInt();
    if (indexOfResidentInRoom == -1) {
      controller.evictFromTheRoom(numberOfRoom);
      return;
    }
    controller.evictFromTheRoom(numberOfRoom, indexOfResidentInRoom);
  }
}
